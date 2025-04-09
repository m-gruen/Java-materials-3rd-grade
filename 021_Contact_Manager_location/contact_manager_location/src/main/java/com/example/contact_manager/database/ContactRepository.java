package com.example.contact_manager.database;

import com.example.contact_manager.model.Contact;
import com.example.contact_manager.model.ContactType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private Connection connection;

    public ContactRepository() {
        connection = Database.getInstance().getConnection();
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        String sql = """
                SELECT * 
                FROM Contact
                """;

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                contactList.add(new Contact(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("phone"),
                                rs.getString("address"),
                                ContactType.valueOf(rs.getString("contactType")),
                                rs.getString("plz") != null ? new LocationRepository().getLocationByPlz(rs.getString("plz")) : null
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactList;
    }

    public void addContact(String name, String phone, String address, ContactType type, String plz) {
        String sql = """
                INSERT INTO Contact (
                       name,
                       phone,
                       address,
                       contactType,
                       plz)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, address);
            pstmt.setString(4, type.name());
            pstmt.setString(5, plz);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateContact(Contact contact) {
        String sql = """
                UPDATE Contact
                SET name = ?,
                    phone = ?,
                    address = ?,
                    contactType = ?,
                    plz = ?
                WHERE id = ?
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getPhone());
            pstmt.setString(3, contact.getAddress());
            pstmt.setString(4, contact.getType().name());
            pstmt.setInt(5, contact.getId());
            pstmt.setString(6, contact.getLocation() != null ? contact.getLocation().getPlz() : null);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(int id) {
        String sql = """
                DELETE FROM Contact
                WHERE id = ?
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}