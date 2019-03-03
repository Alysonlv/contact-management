package com.contact.management.repository;

import com.contact.management.domain.to.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ContactManagerRepositoryTest {

    private static final String PATH = "src/test/resources/addresses/";
    
    private static ContactManagementUser user;
    
    private static ContactManagerRepository repository;
    
    @BeforeEach
    void setUp() {
        user = ImmutableContactManagementUser.builder().username("arya.stark").build();
        repository = new ContactManagerRepository(PATH);
        repository.deleteAddressBook(user);
    }

    /**
     * Get an inexistent address book
     * @return
     */
    @Test
    void getAllContacts_EmptyAddressBook() {
        List<ContactRecord> list = repository.getAllContacts(user);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void saveContact() {
        
        Contact personalContact = ImmutableContact.builder()
                .email("teste.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record = ImmutableContactRecord.builder()
                .firstName("Arya")
                .lastName("Stark")
                .personalContact(personalContact)
                .build();

        List<ContactRecord> list = repository.saveContact(user, record);

        Assertions.assertEquals(1, list.size());
        ContactRecord recordWithId = record.defineId();
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(recordWithId)));
        Assertions.assertTrue(repository.deleteAddressBook(user));
    }

    @Test
    void getContactByFirstName() {
        Contact personalContact = ImmutableContact.builder()
                .email("teste.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record = ImmutableContactRecord.builder()
                .firstName("Arya")
                .lastName("Stark")
                .personalContact(personalContact)
                .build();

        List<ContactRecord> list = repository.saveContact(user, record);

        Contact personalContact2 = ImmutableContact.builder()
                .email("test2.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record2 = ImmutableContactRecord.builder()
                .firstName("Sansa")
                .lastName("Stark")
                .personalContact(personalContact2)
                .build();

        list = repository.saveContact(user, record2);

        Assertions.assertEquals(2, list.size());
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record.defineId())));
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record2.defineId())));

        List<ContactRecord> contactRecords = repository.getContactByFirstName(user,"Arya");

        Assertions.assertTrue(!contactRecords.isEmpty());
        Assertions.assertEquals("Arya", contactRecords.get(0).firstName());

        Assertions.assertTrue(repository.deleteAddressBook(user));
    }

    @Test
    void getContactByFirstName_InvalidName() {
        ContactManagementUser user = ImmutableContactManagementUser.builder().username("arya.stark").build();

        ContactManagerRepository repository = new ContactManagerRepository(PATH);

        Contact personalContact = ImmutableContact.builder()
                .email("teste.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record = ImmutableContactRecord.builder()
                .firstName("Arya")
                .lastName("Stark")
                .personalContact(personalContact)
                .build();

        List<ContactRecord> list = repository.saveContact(user, record);

        Contact personalContact2 = ImmutableContact.builder()
                .email("test2.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record2 = ImmutableContactRecord.builder()
                .firstName("Sansa")
                .lastName("Stark")
                .personalContact(personalContact2)
                .build();

        list = repository.saveContact(user, record2);

        Assertions.assertEquals(2, list.size());
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record.defineId())));
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record2.defineId())));

        List<ContactRecord> contactRecords = repository.getContactByFirstName(user, "John");

        Assertions.assertTrue(contactRecords.isEmpty());
        Assertions.assertTrue(repository.deleteAddressBook(user));
    }

    @Test
    void getContactByFirstName_EmptyAddressBook() {
        ContactManagementUser user = ImmutableContactManagementUser.builder().username("arya.stark").build();
        ContactManagerRepository repository = new ContactManagerRepository(PATH);
        List<ContactRecord> contactRecords = repository.getContactByFirstName(user, "John");
        Assertions.assertTrue(contactRecords.isEmpty());
    }

    @Test
    void getContactByLastName() {
        

        Contact personalContact = ImmutableContact.builder()
                .email("teste.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record = ImmutableContactRecord.builder()
                .firstName("Arya")
                .lastName("Stark")
                .personalContact(personalContact)
                .build();

        List<ContactRecord> list = repository.saveContact(user, record);

        Contact personalContact2 = ImmutableContact.builder()
                .email("test2.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record2 = ImmutableContactRecord.builder()
                .firstName("Sansa")
                .lastName("Stark")
                .personalContact(personalContact2)
                .build();

        list = repository.saveContact(user, record2);

        Assertions.assertEquals(2, list.size());
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record.defineId())));
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record2.defineId())));

        List<ContactRecord> contactRecords = repository.getContactByLastName(user, "Stark");

        Assertions.assertTrue(!contactRecords.isEmpty());
        Assertions.assertEquals(2, contactRecords.size());

        Assertions.assertTrue(repository.deleteAddressBook(user));
    }

    @Test
    void getContactByPersonalPhone() {
        ContactManagementUser user = ImmutableContactManagementUser.builder().username("arya.stark").build();

        ContactManagerRepository repository = new ContactManagerRepository(PATH);

        Contact personalContact = ImmutableContact.builder()
                .email("teste.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record = ImmutableContactRecord.builder()
                .firstName("Arya")
                .lastName("Stark")
                .personalContact(personalContact)
                .build();

        List<ContactRecord> list = repository.saveContact(user, record);

        Contact personalContact2 = ImmutableContact.builder()
                .email("test2.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record2 = ImmutableContactRecord.builder()
                .firstName("Sansa")
                .lastName("Stark")
                .personalContact(personalContact2)
                .build();

        list = repository.saveContact(user, record2);

        Assertions.assertEquals(2, list.size());

        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record.defineId())));
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record2.defineId())));

        List<ContactRecord> contactRecords = repository.getContactByPersonalPhone(user, "5551010");

        Assertions.assertTrue(!contactRecords.isEmpty());
        Assertions.assertEquals("5551010", contactRecords.get(0).personalContact().phoneNumber());

        Assertions.assertTrue(repository.deleteAddressBook(user));
    }

    @Test
    void updateContact() {
        

        Contact personalContact = ImmutableContact.builder()
                .email("teste.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record = ImmutableContactRecord.builder()
                .firstName("Arya")
                .lastName("Stark")
                .personalContact(personalContact)
                .build();

        List<ContactRecord> list = repository.saveContact(user, record);

        Contact personalContact2 = ImmutableContact.builder()
                .email("test2.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record2 = ImmutableContactRecord.builder()
                .firstName("Sansa")
                .lastName("Stark")
                .personalContact(personalContact2)
                .build();

        list = repository.saveContact(user, record2);

        Assertions.assertEquals(2, list.size());
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record.defineId())));
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record2.defineId())));

        Contact updatedContact = ImmutableContact.builder()
                .email("sansa.stark@email.com")
                .phoneNumber("5552020")
                .build();

        ContactRecord updatedRecord = ImmutableContactRecord.builder()
                .firstName("Sansa")
                .lastName("Stark")
                .personalContact(updatedContact)
                .build();

        List<ContactRecord> contactRecords = repository.updateContact(user, record2.hashCode(), updatedRecord);

        Assertions.assertEquals(2, contactRecords.size());
        Assertions.assertTrue(contactRecords.stream().anyMatch(contactRecord -> updatedRecord.defineId().equals(contactRecord.defineId())));
        Assertions.assertFalse(contactRecords.stream().anyMatch(contactRecord -> updatedRecord.defineId().equals(record2.defineId())));

        Assertions.assertTrue(repository.deleteAddressBook(user));
    }

    @Test
    void deleteAddressBook() {
        

        Contact personalContact = ImmutableContact.builder()
                .email("teste.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record = ImmutableContactRecord.builder()
                .firstName("Arya")
                .lastName("Stark")
                .personalContact(personalContact)
                .build();

        List<ContactRecord> list = repository.saveContact(user, record);

        Contact personalContact2 = ImmutableContact.builder()
                .email("test2.email@email.com")
                .phoneNumber("5551010")
                .build();

        ContactRecord record2 = ImmutableContactRecord.builder()
                .firstName("Sansa")
                .lastName("Stark")
                .personalContact(personalContact2)
                .build();

        list = repository.saveContact(user, record2);

        Assertions.assertEquals(2, list.size());
        ContactRecord recordWithId = record.defineId();
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(recordWithId)));

        ContactRecord record2WithId = record2.defineId();
        Assertions.assertTrue(list.stream().anyMatch(contactRecord -> contactRecord.equals(record2WithId)));

        List<ContactRecord> contactRecords = repository.deleteContact(user, record.hashCode());
        Assertions.assertEquals(1, contactRecords.size());
        Assertions.assertFalse(contactRecords.stream().anyMatch(contactRecord -> contactRecord.equals(recordWithId)));
        Assertions.assertTrue(contactRecords.stream().anyMatch(contactRecord -> contactRecord.equals(record2WithId)));


        Assertions.assertTrue(repository.deleteAddressBook(user));
    }
}