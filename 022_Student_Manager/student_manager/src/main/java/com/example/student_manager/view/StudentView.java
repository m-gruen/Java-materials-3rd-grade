package com.example.student_manager.view;

import com.example.student_manager.model.Course;
import com.example.student_manager.model.Student;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StudentView {
    // Root
    private final VBox root = new VBox();

    // Search
    private final HBox hBoxSearch = new HBox();
    private final TextField tfSearchText = new TextField();
    private final Button btnSearch = new Button("Search");

    // Main content container (horizontal)
    private final HBox hBoxContent = new HBox();

    // Left side - Student Table
    private final VBox vBoxLeft = new VBox();
    private final TableView<Student> tvStudents = new TableView<>();
    private final TableColumn<Student, Integer> colId = new TableColumn<>("ID");
    private final TableColumn<Student, String> colFirstName = new TableColumn<>("First Name");
    private final TableColumn<Student, String> colLastName = new TableColumn<>("Last Name");

    // Right side - Detail View
    private final VBox vBoxDetail = new VBox();
    private final Label lblDetailHeader = new Label("Student Details");

    private final HBox hBoxId = new HBox();
    private final Label lblId = new Label("ID:");
    private final TextField tfId = new TextField();

    private final HBox hBoxFirstName = new HBox();
    private final Label lblFirstName = new Label("First Name:");
    private final TextField tfFirstName = new TextField();

    private final HBox hBoxLastName = new HBox();
    private final Label lblLastName = new Label("Last Name:");
    private final TextField tfLastName = new TextField();

    // Courses - Now with ListView and selection controls
    private final VBox vBoxCourses = new VBox();
    private final Label lblCourses = new Label("Enrolled Courses:");
    private final ListView<Course> lvEnrolledCourses = new ListView<>();

    // Course Selection Controls
    private final HBox hBoxCourseSelection = new HBox();
    private final ComboBox<Course> cbAvailableCourses = new ComboBox<>();
    private final Button btnAddCourse = new Button("+");
    private final Button btnRemoveCourse = new Button("-");

    // Important Buttons
    private final HBox hBoxButtons = new HBox();
    private final Button btnNew = new Button("New");
    private final Button btnEdit = new Button("Edit");
    private final Button btnSave = new Button("Save");
    private final Button btnCancel = new Button("Cancel");
    private final Button btnDelete = new Button("Delete");

    public StudentView() {
        init();
    }

    public void init() {
        // Root
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        // Search
        hBoxSearch.setSpacing(10);
        tfSearchText.setPrefWidth(300);
        hBoxSearch.getChildren().addAll(tfSearchText, btnSearch);

        // Student Table configuration
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        // Set column widths
        colId.setPrefWidth(50);
        colFirstName.setPrefWidth(150);
        colLastName.setPrefWidth(150);

        // Add columns to table
        tvStudents.getColumns().addAll(colId, colFirstName, colLastName);

        // Fixed size for TableView
        tvStudents.setPrefHeight(500);
        tvStudents.setMaxHeight(500);
        tvStudents.setMinHeight(500);
        tvStudents.setPrefWidth(350);
        tvStudents.setMaxWidth(350);
        tvStudents.setMinWidth(350);

        // Add table to left box - now with fixed size
        vBoxLeft.getChildren().add(tvStudents);
        vBoxLeft.setPrefWidth(350);
        vBoxLeft.setMinWidth(350);
        vBoxLeft.setMaxWidth(350);

        // Detail View - make it much wider
        vBoxDetail.setSpacing(15);
        vBoxDetail.setPadding(new Insets(0, 0, 0, 30));
        vBoxDetail.setPrefWidth(800); // Increased from 500
        vBoxDetail.setMinWidth(800); // Added min width
        HBox.setHgrow(vBoxDetail, Priority.ALWAYS);

        // Add header for detail view
        lblDetailHeader.setFont(Font.font("System", FontWeight.BOLD, 18)); // Larger font
        lblDetailHeader.setPadding(new Insets(0, 0, 20, 0)); // More padding
        vBoxDetail.getChildren().add(lblDetailHeader);

        // Make labels wider and text fields bigger
        int labelWidth = 150;
        int fieldWidth = 600; // Bigger fields

        hBoxId.setSpacing(15);
        lblId.setPrefWidth(labelWidth);
        tfId.setPrefWidth(fieldWidth);
        hBoxId.getChildren().addAll(lblId, tfId);
        tfId.setEditable(false);
        tfId.setDisable(true);

        hBoxFirstName.setSpacing(15);
        lblFirstName.setPrefWidth(labelWidth);
        tfFirstName.setPrefWidth(fieldWidth);
        hBoxFirstName.getChildren().addAll(lblFirstName, tfFirstName);

        hBoxLastName.setSpacing(15);
        lblLastName.setPrefWidth(labelWidth);
        tfLastName.setPrefWidth(fieldWidth);
        hBoxLastName.getChildren().addAll(lblLastName, tfLastName);

        // Course section - make it bigger
        vBoxCourses.setSpacing(15);
        lblCourses.setPrefWidth(labelWidth);

        // Make the enrolled courses list larger
        lvEnrolledCourses.setPrefHeight(180);
        lvEnrolledCourses.setPrefWidth(fieldWidth);

        // Setup ComboBox and buttons for course selection - make ComboBox much wider
        cbAvailableCourses.setPromptText("Select a course");
        cbAvailableCourses.setPrefWidth(fieldWidth - 100); // Wider combo box
        btnAddCourse.setPrefWidth(45);
        btnAddCourse.setPrefHeight(30);
        btnRemoveCourse.setPrefWidth(45);
        btnRemoveCourse.setPrefHeight(30);

        hBoxCourseSelection.setSpacing(15);
        hBoxCourseSelection.getChildren().addAll(cbAvailableCourses, btnAddCourse, btnRemoveCourse);

        // Add course controls to the courses section
        vBoxCourses.getChildren().addAll(lblCourses, lvEnrolledCourses, hBoxCourseSelection);

        // Add all form sections to the detail view
        vBoxDetail.getChildren().addAll(hBoxId, hBoxFirstName, hBoxLastName, vBoxCourses);

        // Buttons
        hBoxButtons.setSpacing(15);
        hBoxButtons.setPadding(new Insets(20, 0, 0, 0)); // More padding

        // Make buttons bigger
        for (Button btn : new Button[] { btnNew, btnEdit, btnSave, btnCancel, btnDelete }) {
            btn.setPrefWidth(100);
            btn.setPrefHeight(35);
        }

        hBoxButtons.getChildren().addAll(btnNew, btnEdit, btnSave, btnCancel, btnDelete);

        // Add buttons to detail view
        vBoxDetail.getChildren().add(hBoxButtons);

        // Create horizontal layout for table and details
        hBoxContent.getChildren().addAll(vBoxLeft, vBoxDetail);
        hBoxContent.setSpacing(20); // Increased spacing

        // Generate root view with search at top and content below
        root.getChildren().addAll(hBoxSearch, hBoxContent);
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

    public TableView<Student> getTvStudents() {
        return tvStudents;
    }

    public TextField getTfId() {
        return tfId;
    }

    public TextField getTfFirstName() {
        return tfFirstName;
    }

    public TextField getTfLastName() {
        return tfLastName;
    }

    public ListView<Course> getLvEnrolledCourses() {
        return lvEnrolledCourses;
    }

    public ComboBox<Course> getCbAvailableCourses() {
        return cbAvailableCourses;
    }

    public Button getBtnAddCourse() {
        return btnAddCourse;
    }

    public Button getBtnRemoveCourse() {
        return btnRemoveCourse;
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

    public Button getBtnCancel() {
        return btnCancel;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void clearFields() {
        tfId.clear();
        tfFirstName.clear();
        tfLastName.clear();
        lvEnrolledCourses.getItems().clear();
    }
}