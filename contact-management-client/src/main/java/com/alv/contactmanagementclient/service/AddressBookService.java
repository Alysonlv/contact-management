package com.alv.contactmanagementclient.service;
/*
 * Created by alysonlv - 2019-03-02
 */

import com.alv.contactmanagementclient.service.domain.*;
import com.contact.management.domain.to.ContactManagementUser;
import com.contact.management.domain.to.ContactRecord;
import com.contact.management.service.ContactManageService;
import com.contact.management.service.ContactManageServiceImpl;

import java.util.List;

/*
Primary interface that is responsible for create the responses
 */
public class AddressBookService {

    private ContactManageService service;

    private static final String PATH = "src/test/resources/addresses/";

    public AddressBookService(String path) {
        this.service = new ContactManageServiceImpl(path);
    }

    public BookAddressResponse getAddressBook(ContactManagementUser user) {
        BookAddressResponse response = new BookAddressResponse();
        response.setContactRecords(service.getAllContacts(user));
        response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.OK));
        response.setUser(user);
        return response;
    }

    public CreateContactResponse saveContact(ContactManagementUser user, CreateContactRequest request) {
        CreateContactResponse response = new CreateContactResponse();
        response.setContactRecords(service.saveContact(user, request.getContactRecord()));
        response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.OK));
        response.setUser(user);
        return response;
    }
    
    public BookAddressResponse getContact(ContactManagementUser user, String value) {
        BookAddressResponse response = new BookAddressResponse();
        response.setContactRecords(service.getContact(user, value));
        response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.OK));
        response.setUser(user);
        return response;
    }

    public BookAddressResponse getContactByFirstName(ContactManagementUser user, String value) {
        BookAddressResponse response = new BookAddressResponse();
        response.setContactRecords(service.getContactByFirstName(user, value));
        response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.OK));
        response.setUser(user);
        return response;
    }

    public BookAddressResponse getContactByLastName(ContactManagementUser user, String value) {
        BookAddressResponse response = new BookAddressResponse();
        response.setContactRecords(service.getContactByLastName(user, value));
        response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.OK));
        response.setUser(user);
        return response;
    }

    public BookAddressResponse getContactByPersonalPhone(ContactManagementUser user, String phone) {
        BookAddressResponse response = new BookAddressResponse();
        response.setContactRecords(service.getContactByPersonalPhone(user, phone));
        response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.OK));
        response.setUser(user);
        return response;
    }

    public BookAddressResponse getContactByBusinesslPhone(ContactManagementUser user, String phone) {
        BookAddressResponse response = new BookAddressResponse();
        response.setContactRecords(service.getContactByBusinesslPhone(user, phone));
        response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.OK));
        response.setUser(user);
        return response;
    }
    
    public UpdateContactResponse updateContact(ContactManagementUser user, UpdateContactRequest request) {
        UpdateContactResponse response = new UpdateContactResponse();
        List<ContactRecord> list = service.updateContact(user, request.getContactRecord().id(), request.getContactRecord());

        if (list.isEmpty()) {
            response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.NOK));
            response.setContactRecords(service.getAllContacts(user));
        } else {
            response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.OK));
            response.setContactRecords(list);
        }
        response.setUser(user);
        return response;
    }
    
    public BookAddressResponse deleteContact(ContactManagementUser user, int id) {
        BookAddressResponse response = new BookAddressResponse();
        response.setContactRecords(service.deleteContact(user, id));
        response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.OK));
        response.setUser(user);
        return response;
    }
    
    public BookAddressResponse deleteAddressBook(ContactManagementUser user) {
        service.deleteAddressBook(user);

        BookAddressResponse response = new BookAddressResponse();
        response.setContactRecords(service.getAllContacts(user));

        if (response.getContactRecords().isEmpty()) {
            response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.OK));
        } else {
            response.setStatus(new MessageStatus(MessageStatus.MessageStatusCode.NOK));
        }

        response.setUser(user);

        return response;
    }

    public boolean resetBookAddress() {
        return service.resetAddressBook();
    }
}
