package com.example.contact_manager.view;

import com.example.contact_manager.database.ContactRepository;
import com.example.contact_manager.modle.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        view.getLvContacts().setItems(contactList);
        // Detail view binding will be handled in the listener

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
        // Add listener to react to selection in the list view
        view.getLvContacts().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedContact = newValue;
                        isNewContact = false;

                        view.getTfId().setText(String.valueOf(selectedContact.getId()));
                        view.getTfName().setText(selectedContact.getName());
                        view.getTfPhone().setText(selectedContact.getPhone());
                        view.getTfAddress().setText(selectedContact.getAddress());

                        setFieldsEditable(false);
                    } else {
                        view.clearFields();
                    }
                });
    }

    private void init() {
        // load contacts from db
        reloadContacts();
    }

    private void reloadContacts() {
        contactList.clear();
        contactList.addAll(contactRepository.getAllContacts());
    }

    private void searchContact() {
        String searchText = view.getTfSearchText().getText().toLowerCase();
        if (!searchText.isEmpty()) {
            for (Contact contact : contactList) {
                if (contact.getName().toLowerCase().contains(searchText)) {
                    view.getLvContacts().getSelectionModel().select(contact);
                    view.getLvContacts().scrollTo(contact);
                    break;
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

        if (isNewContact) {
            contactRepository.addContact(name, phone, address);
            reloadContacts();

            Contact lastAdded = contactList.getLast();
            view.getLvContacts().getSelectionModel().select(lastAdded);
            view.getLvContacts().scrollTo(lastAdded);
        } else {
            selectedContact.setName(name);
            selectedContact.setPhone(phone);
            selectedContact.setAddress(address);
            contactRepository.updateContact(selectedContact);

            reloadContacts();

            for (Contact c : contactList) {
                if (c.getId() == selectedContact.getId()) {
                    view.getLvContacts().getSelectionModel().select(c);
                    view.getLvContacts().scrollTo(c);
                    break;
                }
            }
        }

        setFieldsEditable(false);
        isNewContact = false;
    }

    private void deleteContact() {
        if (selectedContact != null) {
            contactRepository.deleteContact(selectedContact.getId());

            reloadContacts();

            view.getLvContacts().getSelectionModel().clearSelection();
            view.clearFields();
        }
    }

    private void setFieldsEditable(boolean editable) {
        view.getTfName().setEditable(editable);
        view.getTfPhone().setEditable(editable);
        view.getTfAddress().setEditable(editable);

        view.getBtnSave().setDisable(!editable);
    }

    public static void show(Stage stage) {
        ContactView view = new ContactView();
        ContactPresenter controller = new ContactPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(ContactPresenter.class.getResource("/com/example/contact_manager/styles.css").toExternalForm());

        // Set window size
        stage.setWidth(1000);
        stage.setHeight(1200);

        stage.setTitle("Contact Manager");
        stage.setScene(scene);
        stage.show();
    }
}