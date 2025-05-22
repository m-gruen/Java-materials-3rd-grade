package com.example.data_analyzer.view;

import com.example.data_analyzer.database.CourseRepository;
import com.example.data_analyzer.database.StudentRepository;
import com.example.data_analyzer.model.Course;
import com.example.data_analyzer.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StudentPresenter {

    private final StudentView view;

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private final ObservableList<Course> availableCoursesList = FXCollections.observableArrayList();
    private final ObservableList<Course> tempSelectedCourses = FXCollections.observableArrayList(); // For new students
    private Student selectedStudent;
    private boolean isNewStudent = false;

    private StudentPresenter(StudentView view) {
        this.view = view;

        this.studentRepository = new StudentRepository();
        this.courseRepository = new CourseRepository();

        bindViewToModel();
        attachEvents();
        addListeners();
        init();
    }

    private void bindViewToModel() {
        view.getTvStudents().setItems(studentList);
        view.getCbAvailableCourses().setItems(availableCoursesList);

        // Configure cell factories for better display
        view.getCbAvailableCourses().setButtonCell(new CourseListCell());
        view.getCbAvailableCourses().setCellFactory(listView -> new CourseListCell());

        view.getLvEnrolledCourses().setCellFactory(listView -> new CourseListCell());

        // Enable table row selection
        view.getTvStudents().getSelectionModel().setCellSelectionEnabled(false);
        view.getTvStudents().getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);

        setFieldsEditable(false);
        setCourseControlsEnabled(false);
    }

    private void attachEvents() {
        view.getBtnSearch().setOnAction(event -> searchStudent());
        view.getBtnNew().setOnAction(event -> newStudent());
        view.getBtnEdit().setOnAction(event -> editStudent());
        view.getBtnSave().setOnAction(event -> saveStudent());
        view.getBtnCancel().setOnAction(event -> cancelEdit());
        view.getBtnDelete().setOnAction(event -> deleteStudent());

        // Add course management button handlers
        view.getBtnAddCourse().setOnAction(event -> addCourseToStudent());
        view.getBtnRemoveCourse().setOnAction(event -> removeCourseFromStudent());
    }

    private void addListeners() {
        // Add listener to react to selection in the table view
        view.getTvStudents().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedStudent = newValue;
                        isNewStudent = false;

                        view.getTfId().setText(String.valueOf(selectedStudent.getId()));
                        view.getTfFirstName().setText(selectedStudent.getFirstName());
                        view.getTfLastName().setText(selectedStudent.getLastName());

                        // Update enrolled courses list
                        refreshEnrolledCoursesList();

                        setFieldsEditable(false);
                        setCourseControlsEnabled(false);
                    } else {
                        view.clearFields();
                    }
                });
    }

    private void refreshEnrolledCoursesList() {
        if (isNewStudent) {
            // For new students, show temp courses list
            view.getLvEnrolledCourses().setItems(tempSelectedCourses);
        } else if (selectedStudent != null) {
            // For existing students, show courses from the student object
            ObservableList<Course> enrolledCourses = FXCollections.observableArrayList(selectedStudent.getCourses());
            view.getLvEnrolledCourses().setItems(enrolledCourses);
        } else {
            view.getLvEnrolledCourses().getItems().clear();
        }
    }

    private void refreshAvailableCourses() {
        // Get all courses
        availableCoursesList.clear();
        availableCoursesList.addAll(courseRepository.getAllCourses());

        // If new student, remove temp selected courses from available list
        if (isNewStudent && !tempSelectedCourses.isEmpty()) {
            availableCoursesList.removeAll(tempSelectedCourses);
        }
        // If existing student, remove already enrolled courses from the available list
        else if (selectedStudent != null && !selectedStudent.getCourses().isEmpty()) {
            availableCoursesList.removeAll(selectedStudent.getCourses());
        }
    }

    private void init() {
        // Load students from db
        reloadStudents();

        // Load available courses
        refreshAvailableCourses();
    }

    private void reloadStudents() {
        studentList.clear();
        studentList.addAll(studentRepository.getAllStudents());
    }

    private void searchStudent() {
        String searchText = view.getTfSearchText().getText().toLowerCase();
        if (!searchText.isEmpty()) {
            for (Student student : studentList) {
                if (student.getFirstName().toLowerCase().contains(searchText) ||
                        student.getLastName().toLowerCase().contains(searchText)) {
                    view.getTvStudents().getSelectionModel().select(student);
                    view.getTvStudents().scrollTo(student);
                    break;
                }
            }
        }
    }

    private void newStudent() {
        view.clearFields();
        selectedStudent = new Student();
        isNewStudent = true;

        // Clear any previously selected courses in case of multiple new student
        // operations
        tempSelectedCourses.clear();

        // Update the ListView to reflect the empty selection
        refreshEnrolledCoursesList();

        setFieldsEditable(true);
        setCourseControlsEnabled(true); // Enable course selection for new student
        refreshAvailableCourses(); // Load all available courses
    }

    private void editStudent() {
        if (selectedStudent != null) {
            setFieldsEditable(true);
            setCourseControlsEnabled(true);
            refreshAvailableCourses();
        }
    }

    private void cancelEdit() {
        if (selectedStudent != null && !isNewStudent) {
            // Reset fields to original values
            view.getTfFirstName().setText(selectedStudent.getFirstName());
            view.getTfLastName().setText(selectedStudent.getLastName());
            refreshEnrolledCoursesList();
        } else {
            view.clearFields();
            tempSelectedCourses.clear(); // Clear temp courses if creating a new student
        }

        setFieldsEditable(false);
        setCourseControlsEnabled(false);
        isNewStudent = false;
    }

    private void saveStudent() {
        if (view.getTfFirstName().getText().trim().isEmpty() ||
                view.getTfLastName().getText().trim().isEmpty()) {
            showAlert("Input Error", "Please fill in all required fields.", AlertType.ERROR);
            return;
        }

        String firstName = view.getTfFirstName().getText();
        String lastName = view.getTfLastName().getText();

        if (isNewStudent) {
            // Store temp courses for later
            List<Course> coursesToAdd = new ArrayList<>(tempSelectedCourses);

            // Save the new student first
            studentRepository.addStudent(firstName, lastName);

            // Reload students to get the newly created student with ID
            reloadStudents();

            // Find the newly created student (should be the last one)
            Student newlyCreatedStudent = studentList.isEmpty() ? null : studentList.get(studentList.size() - 1);

            if (newlyCreatedStudent != null) {
                // Add the previously selected courses to the student
                for (Course course : coursesToAdd) {
                    studentRepository.enrollStudentInCourse(newlyCreatedStudent.getId(), course.getId());
                    newlyCreatedStudent.getCourses().add(course);
                }

                view.getTvStudents().getSelectionModel().select(newlyCreatedStudent);
                view.getTvStudents().scrollTo(newlyCreatedStudent);
            }

            // Clear the temp list
            tempSelectedCourses.clear();
        } else {
            selectedStudent.setFirstName(firstName);
            selectedStudent.setLastName(lastName);
            studentRepository.updateStudent(selectedStudent);

            reloadStudents();

            for (Student s : studentList) {
                if (s.getId() == selectedStudent.getId()) {
                    view.getTvStudents().getSelectionModel().select(s);
                    view.getTvStudents().scrollTo(s);
                    break;
                }
            }
        }

        setFieldsEditable(false);
        setCourseControlsEnabled(false);
        isNewStudent = false;
    }

    private void deleteStudent() {
        if (selectedStudent != null) {
            studentRepository.deleteStudent(selectedStudent.getId());

            reloadStudents();

            view.getTvStudents().getSelectionModel().clearSelection();
            view.clearFields();
        }
    }

    private void addCourseToStudent() {
        if (view.getCbAvailableCourses().getSelectionModel().isEmpty()) {
            return;
        }

        Course selectedCourse = view.getCbAvailableCourses().getSelectionModel().getSelectedItem();

        if (selectedCourse != null) {
            if (isNewStudent) {
                // For new students, just add to the temp list
                tempSelectedCourses.add(selectedCourse);

                // Update the UI
                refreshEnrolledCoursesList();
                refreshAvailableCourses();

                // Clear the selection
                view.getCbAvailableCourses().getSelectionModel().clearSelection();
            } else if (selectedStudent != null) {
                // For existing students, add to database and student object
                boolean success = studentRepository.enrollStudentInCourse(
                        selectedStudent.getId(), selectedCourse.getId());

                if (success) {
                    // Add to the student's course list in the model
                    selectedStudent.getCourses().add(selectedCourse);

                    // Refresh the UI
                    refreshEnrolledCoursesList();
                    refreshAvailableCourses();

                    // Clear the selection
                    view.getCbAvailableCourses().getSelectionModel().clearSelection();
                } else {
                    showAlert("Enrollment Error",
                            "Could not enroll student in the selected course.",
                            AlertType.ERROR);
                }
            }
        }
    }

    private void removeCourseFromStudent() {
        if (view.getLvEnrolledCourses().getSelectionModel().isEmpty()) {
            return;
        }

        Course selectedCourse = view.getLvEnrolledCourses().getSelectionModel().getSelectedItem();

        if (selectedCourse != null) {
            if (isNewStudent) {
                // For new students, just remove from the temp list
                tempSelectedCourses.remove(selectedCourse);

                // Update the UI
                refreshEnrolledCoursesList();
                refreshAvailableCourses();
            } else if (selectedStudent != null) {
                // For existing students, remove from database and student object
                boolean success = studentRepository.removeStudentFromCourse(
                        selectedStudent.getId(), selectedCourse.getId());

                if (success) {
                    // Remove from the student's course list in the model
                    selectedStudent.getCourses().remove(selectedCourse);

                    // Refresh the UI
                    refreshEnrolledCoursesList();
                    refreshAvailableCourses();
                } else {
                    showAlert("Removal Error",
                            "Could not remove student from the selected course.",
                            AlertType.ERROR);
                }
            }
        }
    }

    private void setFieldsEditable(boolean editable) {
        view.getTfFirstName().setEditable(editable);
        view.getTfLastName().setEditable(editable);

        view.getBtnSave().setDisable(!editable);
        view.getBtnCancel().setDisable(!editable);
        view.getBtnNew().setDisable(editable);
        view.getBtnEdit().setDisable(editable);
        view.getBtnDelete().setDisable(editable);
    }

    private void setCourseControlsEnabled(boolean enabled) {
        view.getCbAvailableCourses().setDisable(!enabled);
        view.getBtnAddCourse().setDisable(!enabled);
        view.getBtnRemoveCourse().setDisable(!enabled);
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void show(Stage stage) {
        StudentView view = new StudentView();
        StudentPresenter presenter = new StudentPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets()
                .add(StudentPresenter.class.getResource("/com/example/data_analyzer/styles.css").toExternalForm());

        // Set window size - wider to accommodate larger detail section
        stage.setWidth(1400);
        stage.setHeight(750);
        stage.setMinWidth(1200);
        stage.setMinHeight(700);

        stage.setTitle("Student Manager");
        stage.setScene(scene);
        stage.show();
    }

    // Inner class for displaying courses in ComboBox and ListView
    private class CourseListCell extends javafx.scene.control.ListCell<Course> {
        @Override
        protected void updateItem(Course course, boolean empty) {
            super.updateItem(course, empty);

            if (empty || course == null) {
                setText(null);
            } else {
                setText(course.getId() + ": " + course.getName());
            }
        }
    }
}
