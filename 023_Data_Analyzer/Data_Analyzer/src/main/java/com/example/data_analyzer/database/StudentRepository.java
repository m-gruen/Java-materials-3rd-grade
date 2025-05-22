package com.example.data_analyzer.database;

import com.example.data_analyzer.model.Student;
import com.example.data_analyzer.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private Connection connection;
    private CourseRepository courseRepository;

    public StudentRepository() {
        connection = Database.getInstance().getConnection();
        courseRepository = new CourseRepository();
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String sql = """
                SELECT *
                FROM Student
                """;

        try (Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("ID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                loadStudentCourses(student);
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public Student getStudentById(int id) {
        String sql = """
                SELECT *
                FROM Student
                WHERE ID = ?
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Student student = new Student(
                        rs.getInt("ID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                loadStudentCourses(student);
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadStudentCourses(Student student) {
        String sql = """
                SELECT c.*
                FROM Course c
                JOIN CoursesOfStudents cs ON c.ID = cs.CourseID
                WHERE cs.StudentID = ?
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Course course = new Course(
                        rs.getString("ID"),
                        rs.getString("Name"));
                student.getCourses().add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudent(String firstName, String lastName) {
        String sql = """
                INSERT INTO Student (
                       FirstName,
                       LastName)
                VALUES (?, ?)
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.executeUpdate();

            // Get auto-generated ID
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // If needed for further processing
                    int id = generatedKeys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        String sql = """
                UPDATE Student
                SET FirstName = ?,
                    LastName = ?
                WHERE ID = ?
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setInt(3, student.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        // First delete student's course relationships
        String deleteRelationshipsSql = """
                DELETE FROM CoursesOfStudents
                WHERE StudentID = ?
                """;

        String deleteStudentSql = """
                DELETE FROM Student
                WHERE ID = ?
                """;

        try (PreparedStatement pstmtRelationships = connection.prepareStatement(deleteRelationshipsSql);
                PreparedStatement pstmtStudent = connection.prepareStatement(deleteStudentSql)) {

            // Delete relationships first
            pstmtRelationships.setInt(1, id);
            pstmtRelationships.executeUpdate();

            // Then delete the student
            pstmtStudent.setInt(1, id);
            pstmtStudent.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Enrolls a student in a course by adding an entry to the CoursesOfStudents
     * table
     * Uses MERGE INTO to handle duplicates gracefully
     * 
     * @param studentId The ID of the student
     * @param courseId  The ID of the course
     * @return True if the enrollment was successful, false otherwise
     */
    public boolean enrollStudentInCourse(int studentId, String courseId) {
        String sql = """
                MERGE INTO CoursesOfStudents KEY(StudentID, CourseID)
                VALUES (?, ?)
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, courseId);
            int rowsAffected = pstmt.executeUpdate();
            return true; // MERGE always succeeds, even if no insert was performed
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes a student from a course by deleting the entry from the
     * CoursesOfStudents table
     * 
     * @param studentId The ID of the student
     * @param courseId  The ID of the course
     * @return True if the removal was successful, false otherwise
     */
    public boolean removeStudentFromCourse(int studentId, String courseId) {
        String sql = """
                DELETE FROM CoursesOfStudents
                WHERE StudentID = ? AND CourseID = ?
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, courseId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
