package com.example.contact_manager.view;

import com.example.contact_manager.model.Contact;
import com.example.contact_manager.model.ContactType;
import com.example.contact_manager.model.Location;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContactView {
    // root
    private final VBox root = new VBox();

    // Search
    private final HBox hBoxSearch = new HBox();
    private final TextField tfSearchText = new TextField();
    private final Button btnSearch = new Button("Search");

    // Contact Tree
    private final TreeView<Object> tvContacts = new TreeView<>();

    // Detail View
    private final VBox vBoxDetail = new VBox();

    private final HBox hBoxId = new HBox();
    private final Label lblId = new Label("ID:");
    private final TextField tfId = new TextField();

    private final HBox hBoxName = new HBox();
    private final Label lblName = new Label("Name:");
    private final TextField tfName = new TextField();

    private final HBox hBoxPhone = new HBox();
    private final Label lblPhone = new Label("Phone:");
    private final TextField tfPhone = new TextField();

    private final HBox hBoxAddress = new HBox();
    private final Label lblAddress = new Label("Address:");
    private final TextField tfAddress = new TextField();

    private final HBox hBoxLocation = new HBox();
    private final Label lblLocation = new Label("Location:");
    private final ComboBox<Location> cmbLocation = new ComboBox<>();

    private final HBox hBoxType = new HBox();
    private final Label lblType = new Label("Type:");
    private final ComboBox<ContactType> cmbContactType = new ComboBox<>();

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

        // Contact Tree
        tvContacts.setPrefHeight(600);

        // Detail View
        hBoxId.setSpacing(10);
        lblId.setPrefWidth(100);
        tfId.setPrefWidth(600);
        hBoxId.getChildren().addAll(lblId, tfId);
        tfId.setEditable(false);
        tfId.setDisable(true);

        hBoxName.setSpacing(10);
        lblName.setPrefWidth(100);
        tfName.setPrefWidth(600);
        hBoxName.getChildren().addAll(lblName, tfName);

        hBoxPhone.setSpacing(10);
        lblPhone.setPrefWidth(100);
        tfPhone.setPrefWidth(600);
        hBoxPhone.getChildren().addAll(lblPhone, tfPhone);

        hBoxAddress.setSpacing(10);
        lblAddress.setPrefWidth(100);
        tfAddress.setPrefWidth(600);
        hBoxAddress.getChildren().addAll(lblAddress, tfAddress);

        hBoxLocation.setSpacing(10);
        lblLocation.setPrefWidth(100);
        cmbLocation.setPrefWidth(600);
        hBoxLocation.getChildren().addAll(lblLocation, cmbLocation);

        hBoxType.setSpacing(10);
        lblType.setPrefWidth(100);
        cmbContactType.setPrefWidth(600);
        hBoxType.getChildren().addAll(lblType, cmbContactType);

        vBoxDetail.setSpacing(10);
        vBoxDetail.getChildren().addAll(hBoxId, hBoxName, hBoxPhone, hBoxAddress, hBoxLocation, hBoxType);

        // Important Buttons
        hBoxButtons.setSpacing(10);
        hBoxButtons.getChildren().addAll(btnNew, btnEdit, btnSave, btnDelete);

        // Generate root view
        root.getChildren().addAll(hBoxSearch, tvContacts, vBoxDetail, hBoxButtons);

        // Initialize ComboBox with ContactType values
        cmbContactType.getItems().addAll(ContactType.values());
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

    public TreeView<Object> getTvContacts() {
        return tvContacts;
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

    public ComboBox<ContactType> getCmbContactType() {
        return cmbContactType;
    }

    public ComboBox<Location> getCmbLocation() {
        return cmbLocation;
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
        cmbContactType.setValue(null);
        cmbLocation.setValue(null);
    }
}
