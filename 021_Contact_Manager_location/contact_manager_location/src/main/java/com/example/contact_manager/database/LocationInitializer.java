package com.example.contact_manager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class LocationInitializer {
    private final Connection connection;
    private final LocationRepository locationRepository;

    public LocationInitializer() {
        this.connection = Database.getInstance().getConnection();
        this.locationRepository = new LocationRepository();
    }

    public void populateLocations() {
        Map<String, String> locations = new HashMap<>();
        locations.put("4020", "Linz");
        locations.put("4060", "Leonding");
        locations.put("4050", "Traun");
        locations.put("4040", "Linz-Urfahr");
        locations.put("4030", "Linz-Süd");
        locations.put("1010", "Wien-Innere Stadt");
        locations.put("1150", "Wien-Rudolfsheim");
        locations.put("8010", "Graz");
        locations.put("5020", "Salzburg");
        locations.put("6020", "Innsbruck");
        locations.put("1030", "Wien-Landstraße");
        locations.put("1040", "Wien-Wieden");
        locations.put("1050", "Wien-Margareten");
        locations.put("1100", "Wien-Favoriten");
        locations.put("1210", "Wien-Floridsdorf");
        locations.put("2340", "Mödling");
        locations.put("3100", "St. Pölten");
        locations.put("3300", "Amstetten");
        locations.put("4021", "Linz-Zentrum");
        locations.put("4400", "Steyr");
        locations.put("4600", "Wels");
        locations.put("4810", "Gmunden");
        locations.put("5026", "Salzburg-Aigen");
        locations.put("6060", "Hall in Tirol");
        locations.put("6850", "Dornbirn");
        locations.put("7000", "Eisenstadt");
        locations.put("7400", "Oberwart");
        locations.put("8011", "Graz-Andritz");
        locations.put("8430", "Leibnitz");
        locations.put("9020", "Klagenfurt");
        locations.put("9500", "Villach");
        locations.put("9900", "Lienz");
        locations.put("1080", "Wien-Josefstadt");
        locations.put("1120", "Wien-Meidling");
        locations.put("1220", "Wien-Donaustadt");
        locations.put("2000", "Stockerau");
        locations.put("2100", "Korneuburg");
        locations.put("2201", "Gerasdorf bei Wien");
        locations.put("2345", "Brunn am Gebirge");
        locations.put("2500", "Baden");
        locations.put("2700", "Wiener Neustadt");
        locations.put("3002", "Purkersdorf");
        locations.put("3304", "St. Georgen am Ybbsfelde");
        locations.put("3500", "Krems an der Donau");
        locations.put("3910", "Zwettl");
        locations.put("4202", "Hellmonsödt");
        locations.put("4560", "Kirchdorf an der Krems");
        locations.put("4840", "Vöcklabruck");
        locations.put("4910", "Ried im Innkreis");
        locations.put("5023", "Salzburg-Gnigl");
        locations.put("5280", "Braunau am Inn");
        locations.put("6026", "Innsbruck-Amras");
        locations.put("6330", "Kufstein");
        locations.put("6858", "Schwarzach");
        locations.put("7001", "Eisenstadt-Umgebung");
        locations.put("7540", "Güssing");
        locations.put("8054", "Graz-Straßgang");
        locations.put("8600", "Bruck an der Mur");
        locations.put("9400", "Wolfsberg");


        for (Map.Entry<String, String> entry : locations.entrySet()) {
            locationRepository.addLocation(entry.getKey(), entry.getValue());
        }
    }
}
