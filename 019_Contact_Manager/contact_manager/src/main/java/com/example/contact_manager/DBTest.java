package com.example.contact_manager;

import com.example.contact_manager.database.ContactRepository;
import com.example.contact_manager.database.Database;
import com.example.contact_manager.model.Contact;

import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        ContactRepository contactRepository = new ContactRepository();

        contactRepository.addContact("Hans", "123456", "Linz");
        contactRepository.addContact("Max", "987654", "Leonding");
        contactRepository.addContact("Franz", "123987", "Steyr");

        List<Contact> contacts = contactRepository.getAllContacts();

        contacts.forEach(System.out::println);
    }
}
