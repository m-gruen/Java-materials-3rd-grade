package com.example.contact_manager.view;

import com.example.contact_manager.database.ContactRepository;
import com.example.contact_manager.model.Contact;
import com.example.contact_manager.model.ContactType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactPresenter {

    private final ContactView view;
    private final ContactRepository contactRepository;
    private final ObservableList<Contact> contactList = FXCollections.observableArrayList();
    private Contact selectedContact;
    private boolean isNewContact = false;

    private ContactPresenter(ContactView view) {
        this.view = view;
        this.contactRepository = new ContactRepository();
        setFieldsEditable(false);
        attachEvents();
        addListeners();
        init();
    }

    private void attachEvents() {
        view.getBtnSearch().setOnAction(event -> searchContact());
        view.getBtnNew().setOnAction(event -> newContact());
        view.getBtnEdit().setOnAction(event -> editContact());
        view.getBtnSave().setOnAction(event -> saveContact());
        view.getBtnDelete().setOnAction(event -> deleteContact());
    }

    private void addListeners() {
        view.getTvContacts().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.getValue() instanceof Contact) {
                Contact contact = (Contact) newValue.getValue();

                selectedContact = contact;
                isNewContact = false;
                view.getTfId().setText(String.valueOf(contact.getId()));
                view.getTfName().setText(contact.getName());
                view.getTfPhone().setText(contact.getPhone());
                view.getTfAddress().setText(contact.getAddress());
                view.getCmbContactType().setValue(contact.getType());

                setFieldsEditable(false);
            } else {
                view.clearFields();
            }
        });
    }

    private void init() {
        reloadContacts();
    }

    private void reloadContacts() {
        contactList.clear();
        contactList.addAll(contactRepository.getAllContacts());
        buildTreeView();
    }

    private void searchContact() {
        String searchText = view.getTfSearchText().getText().toLowerCase();
        TreeItem<Object> root = view.getTvContacts().getRoot();

        for (TreeItem<Object> typeNode : root.getChildren()) {
            for (TreeItem<Object> contactNode : typeNode.getChildren()) {
                Contact contact = (Contact) contactNode.getValue();

                if (contact.getName().toLowerCase().contains(searchText)) {
                    view.getTvContacts().getSelectionModel().select(contactNode);
                    view.getTvContacts().scrollTo(view.getTvContacts().getRow(contactNode));
                    return;
                }
            }
        }
    }

    private void newContact() {
        view.clearFields();
        selectedContact = new Contact();
        isNewContact = true;
        setFieldsEditable(true);
    }

    private void editContact() {
        if (selectedContact != null) {
            setFieldsEditable(true);
        }
    }

    private void saveContact() {
        if (view.getTfName().getText().trim().isEmpty()) {
            return;
        }

        String name = view.getTfName().getText();
        String phone = view.getTfPhone().getText();
        String address = view.getTfAddress().getText();
        ContactType type = view.getCmbContactType().getValue();

        if (isNewContact) {
            contactRepository.addContact(name, phone, address, type);
        } else {
            selectedContact.setName(name);
            selectedContact.setPhone(phone);
            selectedContact.setAddress(address);
            selectedContact.setType(type);
            contactRepository.updateContact(selectedContact);
        }

        reloadContacts();
        setFieldsEditable(false);
        isNewContact = false;
    }

    private void deleteContact() {
        if (selectedContact != null) {
            contactRepository.deleteContact(selectedContact.getId());
            reloadContacts();
            view.getTvContacts().getSelectionModel().clearSelection();
            view.clearFields();
        }
    }

    private void setFieldsEditable(boolean editable) {
        view.getTfName().setEditable(editable);
        view.getTfPhone().setEditable(editable);
        view.getTfAddress().setEditable(editable);
        view.getCmbContactType().setDisable(!editable);
        view.getBtnSave().setDisable(!editable);
    }

    private void buildTreeView() {
        TreeItem<Object> root = new TreeItem<>(null);
        Map<ContactType, List<Contact>> contactsByType = contactList.stream()
                .collect(Collectors.groupingBy(Contact::getType));
        contactsByType.forEach((type, contacts) -> {
            TreeItem<Object> typeNode = new TreeItem<>(type);
            contacts.forEach(contact -> {
                TreeItem<Object> contactNode = new TreeItem<>(contact);
                typeNode.getChildren().add(contactNode);
            });
            typeNode.setExpanded(true);
            root.getChildren().add(typeNode);
        });
        view.getTvContacts().setRoot(root);
        view.getTvContacts().setShowRoot(false);
    }

    public static void show(Stage stage) {
        ContactView view = new ContactView();
        ContactPresenter controller = new ContactPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(ContactPresenter.class.getResource("/com/example/contact_manager/styles.css").toExternalForm());

        stage.setWidth(1000);
        stage.setHeight(1200);
        stage.setTitle("Contact Manager");
        stage.setScene(scene);
        stage.show();
    }
}