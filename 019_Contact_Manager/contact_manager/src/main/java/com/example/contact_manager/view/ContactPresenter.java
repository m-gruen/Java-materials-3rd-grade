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

        // weiter Bindungen für Detailansicht
    }

    private void attachEvents() {
        view.getBtnSearch().setOnAction(event -> searchContact());
    }

    private void addListeners() {
        // reagieren auf eine Selektion in der Liste
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

    public static void show(Stage stage) {
        ContactView view = new ContactView();
        ContactPresenter controller = new ContactPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(ContactPresenter.class.getResource("/com/example/contact_manager/styles.css").toExternalForm());

        // Set window size
        stage.setWidth(800);
        stage.setHeight(1000);

        stage.setTitle("Contact Manager");
        stage.setScene(scene);
        stage.show();
    }

}
