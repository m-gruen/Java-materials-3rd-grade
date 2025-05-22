package com.example.data_analyzer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Course {

    private StringProperty id = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private ObservableList<Student> students = FXCollections.observableArrayList();

    public Course() {
    }

    public Course(String id, String name) {
        setId(id);
        setName(name);
    }

    @Override
    public String toString() {
        return "%s: %s".formatted(
                id.get(),
                name.get());
    }

    // Student relationship methods
    public ObservableList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            // Avoid infinite recursion by checking if student already has this course
            if (!student.getCourses().contains(this)) {
                student.addCourse(this);
            }
        }
    }

    public void removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            // Avoid infinite recursion by checking if student still has this course
            if (student.getCourses().contains(this)) {
                student.removeCourse(this);
            }
        }
    }

    // GETTER and SETTER
    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
