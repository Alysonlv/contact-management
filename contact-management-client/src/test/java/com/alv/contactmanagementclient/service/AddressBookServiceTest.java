package com.alv.contactmanagementclient.service;

import com.alv.contactmanagementclient.service.domain.*;
import com.contact.management.domain.to.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressBookServiceTest {

    private static AddressBookService service;

    private static ContactManagementUser user1;

    private static ContactManagementUser user2;

    @BeforeAll
    static void configure() {
        service = new AddressBookService("src/test/resources/addresses/");
        user1 = ImmutableContactManagementUser.builder().username("user.test.1").build();
        user2 = ImmutableContactManagementUser.builder().username("user.test.2").build();
    }

    @AfterEach
    void clearDabase() {
        service.resetBookAddress();
    }



    @Test
    void saveContact() {
        CreateContactRequest request = new CreateContactRequest();
        Contact personalContact = ImmutableContact.builder().phoneNumber("555-9090").email("email@teste.com").build();
        ContactRecord record = ImmutableContactRecord.builder().firstName("FirstNameContact").lastName("LastNameContact").personalContact(personalContact).build();
        request.setContactRecord(record);

        CreateContactResponse response = service.saveContact(user1, request);

        assertEquals(MessageStatus.MessageStatusCode.OK, response.getStatus().getStatusCode());
        assertEquals(1, response.getContactRecords().size());
        assertEquals("FirstNameContact", response.getContactRecords().get(0).firstName());
    }

    @Test
    void getContact() {
        CreateContactRequest request = new CreateContactRequest();
        Contact personalContact = ImmutableContact.builder().phoneNumber("555-9090").email("email@teste.com").build();
        ContactRecord record = ImmutableContactRecord.builder().firstName("FirstNameContact").lastName("LastNameContact").personalContact(personalContact).build();
        request.setContactRecord(record);

        CreateContactRequest request2 = new CreateContactRequest();
        Contact personalContact2 = ImmutableContact.builder().phoneNumber("555-9091").email("email1@teste.com").build();
        ContactRecord record2 = ImmutableContactRecord.builder().firstName("FirstNameContact1").lastName("LastNameContact").personalContact(personalContact).build();
        request2.setContactRecord(record2);

        service.saveContact(user1, request);
        CreateContactResponse createContactResponse = service.saveContact(user1, request2);

        assertEquals(MessageStatus.MessageStatusCode.OK, createContactResponse.getStatus().getStatusCode());
        assertEquals(2, createContactResponse.getContactRecords().size());

        BookAddressResponse response = service.getContact(user1, "FirstNameContact");
        assertEquals(MessageStatus.MessageStatusCode.OK, response.getStatus().getStatusCode());
        assertEquals(1, response.getContactRecords().size());
        assertEquals("FirstNameContact", response.getContactRecords().get(0).firstName());
    }

    @Test
    void getContactByFirstName() {
        CreateContactRequest request = new CreateContactRequest();
        Contact personalContact = ImmutableContact.builder().phoneNumber("555-9090").email("email@teste.com").build();
        ContactRecord record = ImmutableContactRecord.builder().firstName("FirstNameContact").lastName("LastNameContact").personalContact(personalContact).build();
        request.setContactRecord(record);

        CreateContactRequest request2 = new CreateContactRequest();
        Contact personalContact2 = ImmutableContact.builder().phoneNumber("555-9091").email("email1@teste.com").build();
        ContactRecord record2 = ImmutableContactRecord.builder().firstName("FirstNameContact1").lastName("LastNameContact").personalContact(personalContact).build();
        request2.setContactRecord(record2);

        service.saveContact(user1, request);
        CreateContactResponse createContactResponse = service.saveContact(user1, request2);

        assertEquals(MessageStatus.MessageStatusCode.OK, createContactResponse.getStatus().getStatusCode());
        assertEquals(2, createContactResponse.getContactRecords().size());

        BookAddressResponse response = service.getContactByFirstName(user1, "FirstNameContact");
        assertEquals(MessageStatus.MessageStatusCode.OK, response.getStatus().getStatusCode());
        assertEquals(1, response.getContactRecords().size());
        assertEquals("FirstNameContact", response.getContactRecords().get(0).firstName());
    }

    @Test
    void getContactByLastName() {
        CreateContactRequest request = new CreateContactRequest();
        Contact personalContact = ImmutableContact.builder().phoneNumber("555-9090").email("email@teste.com").build();
        ContactRecord record = ImmutableContactRecord.builder().firstName("FirstNameContact").lastName("LastNameContact").personalContact(personalContact).build();
        request.setContactRecord(record);

        CreateContactRequest request2 = new CreateContactRequest();
        Contact personalContact2 = ImmutableContact.builder().phoneNumber("555-9091").email("email1@teste.com").build();
        ContactRecord record2 = ImmutableContactRecord.builder().firstName("FirstNameContact2").lastName("LastNameContact").personalContact(personalContact2).build();
        request2.setContactRecord(record2);

        service.saveContact(user1, request);
        service.saveContact(user1, request2);

        CreateContactRequest request3 = new CreateContactRequest();
        Contact personalContact3 = ImmutableContact.builder().phoneNumber("555-9091").email("email1@teste.com").build();
        ContactRecord record3 = ImmutableContactRecord.builder().firstName("FirstNameContact3").lastName("LastNameContact3").personalContact(personalContact3).build();
        request3.setContactRecord(record3);

        CreateContactResponse createContactResponse = service.saveContact(user1, request3);

        assertEquals(MessageStatus.MessageStatusCode.OK, createContactResponse.getStatus().getStatusCode());
        assertEquals(3, createContactResponse.getContactRecords().size());

        BookAddressResponse response = service.getContactByLastName(user1, "LastNameContact");
        assertEquals(MessageStatus.MessageStatusCode.OK, response.getStatus().getStatusCode());
        assertEquals(2, response.getContactRecords().size());
    }

    @Test
    void getContactByPersonalPhone() {
        CreateContactRequest request = new CreateContactRequest();
        Contact personalContact = ImmutableContact.builder().phoneNumber("555-9090").email("email@teste.com").build();
        ContactRecord record = ImmutableContactRecord.builder().firstName("FirstNameContact").lastName("LastNameContact").personalContact(personalContact).build();
        request.setContactRecord(record);

        CreateContactRequest request2 = new CreateContactRequest();
        Contact personalContact2 = ImmutableContact.builder().phoneNumber("555-9091").email("email1@teste.com").build();
        ContactRecord record2 = ImmutableContactRecord.builder().firstName("FirstNameContact2").lastName("LastNameContact").personalContact(personalContact2).build();
        request2.setContactRecord(record2);

        service.saveContact(user1, request);
        service.saveContact(user1, request2);

        CreateContactRequest request3 = new CreateContactRequest();
        Contact personalContact3 = ImmutableContact.builder().phoneNumber("555-9092").email("email1@teste.com").build();
        ContactRecord record3 = ImmutableContactRecord.builder().firstName("FirstNameContact3").lastName("LastNameContact3").personalContact(personalContact3).build();
        request3.setContactRecord(record3);

        CreateContactResponse createContactResponse = service.saveContact(user1, request3);

        assertEquals(MessageStatus.MessageStatusCode.OK, createContactResponse.getStatus().getStatusCode());
        assertEquals(3, createContactResponse.getContactRecords().size());

        BookAddressResponse response = service.getContactByPersonalPhone(user1, "555-9092");
        assertEquals(MessageStatus.MessageStatusCode.OK, response.getStatus().getStatusCode());
        assertEquals(1, response.getContactRecords().size());
        assertEquals("FirstNameContact3", response.getContactRecords().get(0).firstName());
    }

    @Test
    void getContactByBusinesslPhone() {
        CreateContactRequest request = new CreateContactRequest();
        Contact personalContact = ImmutableContact.builder().phoneNumber("555-9090").email("email@teste.com").build();
        ContactRecord record = ImmutableContactRecord.builder().firstName("FirstNameContact").lastName("LastNameContact").personalContact(personalContact).build();
        request.setContactRecord(record);

        CreateContactRequest request2 = new CreateContactRequest();
        Contact personalContact2 = ImmutableContact.builder().phoneNumber("555-9091").email("email1@teste.com").build();
        ContactRecord record2 = ImmutableContactRecord.builder().firstName("FirstNameContact2").lastName("LastNameContact").personalContact(personalContact2).build();
        request2.setContactRecord(record2);

        service.saveContact(user1, request);
        service.saveContact(user1, request2);

        CreateContactRequest request3 = new CreateContactRequest();
        Contact personalContact3 = ImmutableContact.builder().phoneNumber("555-9092").email("email1@teste.com").build();
        Contact pbusinessContact3 = ImmutableContact.builder().phoneNumber("555-9093").email("email1@teste.com").build();
        ContactRecord record3 = ImmutableContactRecord.builder().firstName("FirstNameContact3").lastName("LastNameContact3").personalContact(personalContact3).businessContact(pbusinessContact3).build();
        request3.setContactRecord(record3);

        CreateContactResponse createContactResponse = service.saveContact(user1, request3);

        assertEquals(MessageStatus.MessageStatusCode.OK, createContactResponse.getStatus().getStatusCode());
        assertEquals(3, createContactResponse.getContactRecords().size());

        BookAddressResponse response = service.getContactByBusinesslPhone(user1, "555-9093");
        assertEquals(MessageStatus.MessageStatusCode.OK, response.getStatus().getStatusCode());
        assertEquals(1, response.getContactRecords().size());
        assertEquals("FirstNameContact3", response.getContactRecords().get(0).firstName());
    }

    @Test
    void updateContact() {
        CreateContactRequest request = new CreateContactRequest();
        Contact personalContact = ImmutableContact.builder().phoneNumber("555-9090").email("email@teste.com").build();
        ContactRecord record = ImmutableContactRecord.builder().firstName("FirstNameContact").lastName("LastNameContact").personalContact(personalContact).build();
        request.setContactRecord(record);

        CreateContactResponse response = service.saveContact(user1, request);

        assertEquals(MessageStatus.MessageStatusCode.OK, response.getStatus().getStatusCode());
        assertEquals(1, response.getContactRecords().size());
        assertEquals("FirstNameContact", response.getContactRecords().get(0).firstName());

        BookAddressResponse bookAddressResponse = service.getAddressBook(user1);

        int id = bookAddressResponse.getContactRecords().get(0).id();
        UpdateContactRequest updateContactRequest = new UpdateContactRequest();
        Contact personalContactUpdated = ImmutableContact.builder().phoneNumber("555-9090").email("email@teste.com").build();
        ContactRecord contactRecord = ImmutableContactRecord.builder().firstName("Alyson").lastName("LastNameContact").personalContact(personalContact).id(id).build();
        updateContactRequest.setContactRecord(contactRecord);


        UpdateContactResponse updateContactResponse = service.updateContact(user1, updateContactRequest);
        assertEquals(MessageStatus.MessageStatusCode.OK, updateContactResponse.getStatus().getStatusCode());
        assertEquals(1, updateContactResponse.getContactRecords().size());
        assertEquals("Alyson", updateContactResponse.getContactRecords().get(0).firstName());
        assertEquals(record.lastName(), updateContactResponse.getContactRecords().get(0).lastName());
    }

    @Test
    void deleteContact() {
        CreateContactRequest request = new CreateContactRequest();
        Contact personalContact = ImmutableContact.builder().phoneNumber("555-9090").email("email@teste.com").build();
        ContactRecord record = ImmutableContactRecord.builder().firstName("FirstNameContact").lastName("LastNameContact").personalContact(personalContact).build();
        request.setContactRecord(record);

        CreateContactRequest request2 = new CreateContactRequest();
        Contact personalContact2 = ImmutableContact.builder().phoneNumber("555-9091").email("email1@teste.com").build();
        ContactRecord record2 = ImmutableContactRecord.builder().firstName("FirstNameContact1").lastName("LastNameContact").personalContact(personalContact2).build();
        request2.setContactRecord(record2);

        service.saveContact(user1, request);
        CreateContactResponse createContactResponse = service.saveContact(user1, request2);

        assertEquals(MessageStatus.MessageStatusCode.OK, createContactResponse.getStatus().getStatusCode());
        assertEquals(2, createContactResponse.getContactRecords().size());

        BookAddressResponse response = service.getContact(user1, "FirstNameContact");
        assertEquals(MessageStatus.MessageStatusCode.OK, response.getStatus().getStatusCode());
        assertEquals(1, response.getContactRecords().size());
        assertEquals("FirstNameContact", response.getContactRecords().get(0).firstName());

        BookAddressResponse bookAddressResponse = service.deleteContact(user1, response.getContactRecords().get(0).id());

        assertEquals(MessageStatus.MessageStatusCode.OK, bookAddressResponse.getStatus().getStatusCode());
        assertEquals(1, bookAddressResponse.getContactRecords().size());
    }
}