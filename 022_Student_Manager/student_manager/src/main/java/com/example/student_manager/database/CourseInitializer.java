package com.example.student_manager.database;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class CourseInitializer {
    private final Connection connection;
    private final CourseRepository courseRepository;

    public CourseInitializer() {
        this.connection = Database.getInstance().getConnection();
        this.courseRepository = new CourseRepository();
    }

    public void populateCourses() {
        Map<String, String> courses = new HashMap<>();

        // Programming Courses
        courses.put("PRG1", "Programming Basics");
        courses.put("PRG2", "Object-Oriented Programming");
        courses.put("PRG3", "Advanced Programming Concepts");
        courses.put("WEB1", "Web Development Basics");
        courses.put("WEB2", "Advanced Web Development");
        courses.put("MOB1", "Mobile App Development");
        courses.put("GAME1", "Game Development");

        // Database Courses
        courses.put("DB1", "Database Fundamentals");
        courses.put("DB2", "Advanced Database Systems");
        courses.put("SQL1", "SQL Basics");

        // Networking Courses
        courses.put("NET1", "Networking Basics");
        courses.put("NET2", "Advanced Networking");
        courses.put("SEC1", "IT Security Fundamentals");

        // Math Courses
        courses.put("MATH1", "Mathematics 1");
        courses.put("MATH2", "Mathematics 2");
        courses.put("AM1", "Applied Mathematics");
        courses.put("STAT1", "Statistics for IT");

        // Software Engineering
        courses.put("SE1", "Software Engineering Basics");
        courses.put("SE2", "Advanced Software Engineering");
        courses.put("PM1", "Project Management");
        courses.put("QA1", "Software Quality Assurance");

        // Operating Systems
        courses.put("OS1", "Operating Systems Fundamentals");
        courses.put("LINUX1", "Linux Administration");
        courses.put("WIN1", "Windows Server Administration");

        // Hardware
        courses.put("HW1", "Computer Hardware");
        courses.put("EMBED1", "Embedded Systems");
        courses.put("IOT1", "Internet of Things");

        // Theoretical Computer Science
        courses.put("ALGO1", "Algorithms and Data Structures");
        courses.put("THEO1", "Theoretical Computer Science");

        // Business and Soft Skills
        courses.put("BUS1", "Business Fundamentals");
        courses.put("PRES1", "Presentation Techniques");
        courses.put("ENG1", "Technical English");
        courses.put("COMM1", "Communication Skills");

        // Project Work
        courses.put("PROJ1", "Project Work 1");
        courses.put("PROJ2", "Project Work 2");
        courses.put("THESIS", "Diploma Thesis");

        // Specialized Topics
        courses.put("AI1", "Artificial Intelligence Basics");
        courses.put("ML1", "Machine Learning Fundamentals");
        courses.put("CLOUD1", "Cloud Computing");
        courses.put("DEVOPS1", "DevOps Practices");

        // Insert all courses using the addCourse method which now uses MERGE
        for (Map.Entry<String, String> entry : courses.entrySet()) {
            courseRepository.addCourse(entry.getKey(), entry.getValue());
        }
    }
}
