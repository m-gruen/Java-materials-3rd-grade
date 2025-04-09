package com.example.contact_manager.database;

import com.example.contact_manager.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository {
    private Connection connection;

    public LocationRepository() {
        connection = Database.getInstance().getConnection();
    }

    public Location getLocationByPlz(String plz) {
        String sql = """
                SELECT * 
                FROM Location
                WHERE plz = ?
                """;
        Location location = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, plz);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                location = new Location(
                        rs.getString("plz"),
                        rs.getString("city")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public List<Location> getAllLocations() {
        List<Location> contactList = new ArrayList<>();
        String sql = """
                SELECT * 
                FROM Location
                """;

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                contactList.add(new Location(
                                rs.getString("plz"),
                                rs.getString("city")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactList;
    }

    public void addLocation(String plz, String city) {
        String sql = """
                INSERT INTO Location (
                       plz,
                       city)
                VALUES (?, ?)
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, plz);
            pstmt.setString(2, city);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteLocation(String plz) {
        String sql = """
                DELETE FROM Location
                WHERE plz = ?
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, plz);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
