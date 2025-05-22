package com.example.data_analyzer.database;

import com.example.data_analyzer.model.Course;
import com.example.data_analyzer.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private Connection connection;

    public CourseRepository() {
        connection = Database.getInstance().getConnection();
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        String sql = """
                SELECT *
                FROM Course
                """;

        try (Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Course course = new Course(
                        rs.getString("ID"),
                        rs.getString("Name"));
                loadCourseStudents(course);
                courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public Course getCourseById(String id) {
        String sql = """
                SELECT *
                FROM Course
                WHERE ID = ?
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Course course = new Course(
                        rs.getString("ID"),
                        rs.getString("Name"));
                loadCourseStudents(course);
                return course;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadCourseStudents(Course course) {
        String sql = """
                SELECT s.*
                FROM Student s
                JOIN CoursesOfStudents cs ON s.ID = cs.StudentID
                WHERE cs.CourseID = ?
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("ID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                course.getStudents().add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCourse(String id, String name) {
        String sql = """
                MERGE INTO Course (
                       ID,
                       Name)
                VALUES (?, ?)
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCourse(Course course) {
        String sql = """
                UPDATE Course
                SET Name = ?
                WHERE ID = ?
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getName());
            pstmt.setString(2, course.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(String id) {
        // First delete course's student relationships
        String deleteRelationshipsSql = """
                DELETE FROM CoursesOfStudents
                WHERE CourseID = ?
                """;

        String deleteCourseSql = """
                DELETE FROM Course
                WHERE ID = ?
                """;

        try (PreparedStatement pstmtRelationships = connection.prepareStatement(deleteRelationshipsSql);
                PreparedStatement pstmtCourse = connection.prepareStatement(deleteCourseSql)) {

            // Delete relationships first
            pstmtRelationships.setString(1, id);
            pstmtRelationships.executeUpdate();

            // Then delete the course
            pstmtCourse.setString(1, id);
            pstmtCourse.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
