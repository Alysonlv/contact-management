package com.contact.management.service;

import com.contact.management.domain.to.ContactManagementUser;
import com.contact.management.domain.to.ContactRecord;
import com.contact.management.repository.ContactManagerRepository;

import java.util.List;

/*
 * Created by alysonlv - 2019-03-03
 * Interface to expose the Address Book Repository
 */
public class ContactManageServiceImpl implements ContactManageService {

    private  static ContactManagerRepository repository;

    public ContactManageServiceImpl(String path) {
        if (this.repository == null) {
            this.repository = new ContactManagerRepository(path);
        }
    }

    @Override
    public List<ContactRecord> getAllContacts(ContactManagementUser user) {
        return repository.getAllContacts(user);
    }

    @Override
    public List<ContactRecord> saveContact(ContactManagementUser user, ContactRecord contact) {
        return repository.saveContact(user, contact);
    }

    @Override
    public List<ContactRecord> getContactByFirstName(ContactManagementUser user, String firstName) {
        return repository.getContactByFirstName(user, firstName);
    }

    @Override
    public List<ContactRecord> getContactByLastName(ContactManagementUser user, String lastName) {
        return repository.getContactByLastName(user, lastName);
    }

    @Override
    public List<ContactRecord> getContactByPersonalPhone(ContactManagementUser user, String phone) {
        return repository.getContactByPersonalPhone(user, phone);
    }

    @Override
    public List<ContactRecord> getContactByBusinesslPhone(ContactManagementUser user, String phone) {
        return repository.getContactByBusinesslPhone(user, phone);
    }

    @Override
    public List<ContactRecord> updateContact(ContactManagementUser user, int id, ContactRecord uptadedContact) {
        return repository.updateContact(user, id, uptadedContact);
    }

    @Override
    public List<ContactRecord> deleteContact(ContactManagementUser user, int id) {
        return repository.deleteContact(user, id);
    }

    @Override
    public List<ContactRecord> getContact(ContactManagementUser user, String value) {
        return repository.getContact(user, value);
    }

    @Override
    public boolean deleteAddressBook(ContactManagementUser user) {
        return repository.deleteAddressBook(user);
    }

    @Override
    public boolean resetAddressBook() {
        return repository.resetAddressBook();
    }
}
