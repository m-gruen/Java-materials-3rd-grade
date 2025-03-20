package com.example.contact_manager.view;

import com.example.contact_manager.modle.Contact;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContactView {
    // root
    private final VBox root = new VBox();

    // Search
    private final HBox hBoxSearch = new HBox();
    private final TextField tfSearchText = new TextField();
    private final Button btnSearch = new Button("Search");

    // Contact List
    private final ListView<Contact> lvContacts = new ListView<>();

    // Detail View
    private final VBox vBoxDetail = new VBox();

    private final HBox hBoxId = new HBox();
    private final Label lblId = new Label("ID");
    private final TextField tfId = new TextField();

    private final HBox hBoxName = new HBox();
    private final Label lblName = new Label("Name");
    private final TextField tfName = new TextField();

    private final HBox hBoxPhone = new HBox();
    private final Label lblPhone = new Label("Phone");
    private final TextField tfPhone = new TextField();

    private final HBox hBoxAddress = new HBox();
    private final Label lblAddress = new Label("Address");
    private final TextField tfAddress = new TextField();

    // Important Buttons
    private final HBox hBoxButtons = new HBox();
    private final Button btnNew = new Button("New");
    private final Button btnEdit = new Button("Edit");
    private final Button btnSave = new Button("Save");
    private final Button btnDelete = new Button("Delete");

    public ContactView() {
        init();
    }

    public void init() {
        // Root
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        // Search
        hBoxSearch.setSpacing(10);
        hBoxSearch.getChildren().addAll(tfSearchText, btnSearch);

        // Contact List
        lvContacts.setPrefHeight(400);

        // Detail View
        hBoxId.setSpacing(10);
        lblId.setPrefWidth(100);
        hBoxId.getChildren().addAll(lblId, tfId);
        tfId.setEditable(false);

        hBoxName.setSpacing(10);
        lblName.setPrefWidth(100);
        hBoxName.getChildren().addAll(lblName, tfName);

        hBoxPhone.setSpacing(10);
        lblPhone.setPrefWidth(100);
        hBoxPhone.getChildren().addAll(lblPhone, tfPhone);

        hBoxAddress.setSpacing(10);
        lblAddress.setPrefWidth(100);
        hBoxAddress.getChildren().addAll(lblAddress, tfAddress);

        vBoxDetail.setSpacing(10);
        vBoxDetail.getChildren().addAll(hBoxId, hBoxName, hBoxPhone, hBoxAddress);

        // Important Buttons
        hBoxButtons.setSpacing(10);
        hBoxButtons.getChildren().addAll(btnNew, btnEdit, btnSave, btnDelete);

        // Generate root view
        root.getChildren().addAll(hBoxSearch, lvContacts, vBoxDetail, hBoxButtons);
    }

    public VBox getRoot() {
        return root;
    }

    public TextField getTfSearchText() {
        return tfSearchText;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public ListView<Contact> getLvContacts() {
        return lvContacts;
    }

    public TextField getTfId() {
        return tfId;
    }

    public TextField getTfName() {
        return tfName;
    }

    public TextField getTfPhone() {
        return tfPhone;
    }

    public TextField getTfAddress() {
        return tfAddress;
    }

    public Button getBtnNew() {
        return btnNew;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void clearFields() {
        tfId.clear();
        tfName.clear();
        tfPhone.clear();
        tfAddress.clear();
    }
}
