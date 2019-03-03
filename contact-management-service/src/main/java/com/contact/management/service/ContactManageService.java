package com.contact.management.service;

import com.contact.management.domain.to.ContactManagementUser;
import com.contact.management.domain.to.ContactRecord;

import java.util.List;

public interface ContactManageService {

    List<ContactRecord> getAllContacts(ContactManagementUser user);

    List<ContactRecord> saveContact(ContactManagementUser user, ContactRecord contact);

    List<ContactRecord> getContactByFirstName(ContactManagementUser user, String firstName);

    List<ContactRecord> getContactByLastName(ContactManagementUser user, String lastName);

    List<ContactRecord> getContactByPersonalPhone(ContactManagementUser user, String phone);

    List<ContactRecord> getContactByBusinesslPhone(ContactManagementUser user, String phone);

    List<ContactRecord> updateContact(ContactManagementUser user, int hashCode, ContactRecord uptadedContact);

    List<ContactRecord> deleteContact(ContactManagementUser user, int hashCode);

    List<ContactRecord>  getContact(ContactManagementUser user, String value);

    boolean deleteAddressBook(ContactManagementUser user);

    boolean resetAddressBook();
}
