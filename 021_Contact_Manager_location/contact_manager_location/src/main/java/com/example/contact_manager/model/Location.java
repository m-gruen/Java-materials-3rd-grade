package com.example.contact_manager.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Location {
    private StringProperty plz = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();

    public Location() {
    }

    public Location(String plz, String city) {
        setPlz(plz);
        setCity(city);
    }

    @Override
    public String toString() {
        return "%s: %s".formatted(
                plz.get(),
                city.get()
        );
    }

    // GETTER and SETTER
    public String getPlz() {
        return plz.get();
    }

    public StringProperty plzProperty() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz.set(plz);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }
}