package com.example.contact_manager.view;

import com.example.contact_manager.database.ContactRepository;
import com.example.contact_manager.model.Contact;
import com.example.contact_manager.model.ContactType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
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
        bindViewToModel();
        attachEvents();
        addListeners();
        init();
    }

    private void bindViewToModel() {
        setFieldsEditable(false);
    }

    private void attachEvents() {
        view.getBtnSearch().setOnAction(event -> searchContact());
        view.getBtnNew().setOnAction(event -> newContact());
        view.getBtnEdit().setOnAction(event -> editContact());
        view.getBtnSave().setOnAction(event -> saveContact());
        view.getBtnDelete().setOnAction(event -> deleteContact());
    }

    private void addListeners() {
        view.getTvContacts().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null && newValue.getValue() != null) {
                        Contact contact = newValue.getValue();

                        if (contact.getId() == -1) {
                            view.clearFields();
                            return;
                        }

                        selectedContact = contact;
                        isNewContact = false;
                        view.getTfId().setText(String.valueOf(selectedContact.getId()));
                        view.getTfName().setText(selectedContact.getName());
                        view.getTfPhone().setText(selectedContact.getPhone());
                        view.getTfAddress().setText(selectedContact.getAddress());
                        view.getCmbContactType().setValue(selectedContact.getType());
                        setFieldsEditable(false);
                    } else {
                        view.clearFields();
                    }
                });
    }

    private void init() {
        reloadContacts();
        buildTreeView();
    }

    private void reloadContacts() {
        contactList.clear();
        contactList.addAll(contactRepository.getAllContacts());
    }

    private void searchContact() {
        String searchText = view.getTfSearchText().getText().toLowerCase();
        if (searchText.isEmpty()) return;

        TreeItem<Contact> root = view.getTvContacts().getRoot();
        if (root == null) return;

        for (TreeItem<Contact> typeNode : root.getChildren()) {
            for (TreeItem<Contact> contactNode : typeNode.getChildren()) {
                Contact contact = contactNode.getValue();
                if (contact.getName().toLowerCase().contains(searchText)) {
                    view.getTvContacts().getSelectionModel().select(contactNode);
                    view.getTvContacts().scrollTo(
                            view.getTvContacts().getRow(contactNode)
                    );
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
            reloadContacts();
            buildTreeView();
        } else {
            selectedContact.setName(name);
            selectedContact.setPhone(phone);
            selectedContact.setAddress(address);
            selectedContact.setType(type);
            contactRepository.updateContact(selectedContact);
            reloadContacts();
            buildTreeView();
        }

        setFieldsEditable(false);
        isNewContact = false;
    }

    private void deleteContact() {
        if (selectedContact != null) {
            contactRepository.deleteContact(selectedContact.getId());
            reloadContacts();
            buildTreeView();
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
        TreeItem<Contact> root = new TreeItem<>();

        Map<ContactType, List<Contact>> contactsByType = contactList.stream()
                .collect(Collectors.groupingBy(c -> c.getType() != null ? c.getType() : ContactType.NONE));

        contactsByType.forEach((type, contacts) -> {
            Contact typeContact = new Contact();
            typeContact.setId(-1);
            typeContact.setName(type.toString());
            typeContact.setType(type);

            TreeItem<Contact> typeNode = new TreeItem<>(typeContact);

            contacts.forEach(contact -> {
                TreeItem<Contact> contactNode = new TreeItem<>(contact);
                typeNode.getChildren().add(contactNode);
            });

            typeNode.setExpanded(true);
            root.getChildren().add(typeNode);
        });

        view.getTvContacts().setRoot(root);
        view.getTvContacts().setShowRoot(false);

        view.getTvContacts().setCellFactory(tv -> new javafx.scene.control.TreeCell<>() {
            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);

                if (empty || contact == null) {
                    setText(null);
                } else if (contact.getId() == -1) {
                    setText(contact.getName());
                } else {
                    setText(contact.getName());
                }
            }
        });
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