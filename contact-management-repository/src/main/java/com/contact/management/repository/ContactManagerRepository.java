package com.contact.management.repository;
/*
 * Created by alysonlv - 2019-03-02
 */

import com.contact.management.domain.to.ContactManagementUser;
import com.contact.management.domain.to.ContactRecord;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import static com.contact.management.domain.to.utils.ContactMarshaller.marshal;
import static com.contact.management.domain.to.utils.ContactMarshaller.unmarshal;

public class ContactManagerRepository {
    
    private ContactManagerDB db;

    private BiPredicate<String, ContactRecord> validContact = (value, c) -> validFirstName.test(value, c)
            || validLastName.test(value, c)
            || validPersonalPhoneNumber.test(value, c)
            || validPersonalEmail.test(value, c)
            || validBusinessEmail.test(value, c)
            || validBusinessPhoneNumber.test(value, c);


    private static BiPredicate<String, ContactRecord> validFirstName = (value, c) -> value.equalsIgnoreCase(c.firstName());

    private static BiPredicate<String, ContactRecord> validLastName = (value, c) -> value.equalsIgnoreCase(c.lastName());

    private static BiPredicate<String, ContactRecord> validPersonalPhoneNumber = (value, c) -> c.personalContact() != null && c.personalContact().phoneNumber() != null && value.equalsIgnoreCase(c.personalContact().phoneNumber());

    private static BiPredicate<String, ContactRecord> validBusinessPhoneNumber = (value, c) -> c.businessContact() != null && c.businessContact().phoneNumber() != null && value.equalsIgnoreCase(c.businessContact().phoneNumber());

    private static BiPredicate<String, ContactRecord> validPersonalEmail = (value, c) -> c.personalContact() != null && c.personalContact().email() != null && value.equalsIgnoreCase(c.personalContact().email());

    private static BiPredicate<String, ContactRecord> validBusinessEmail = (value, c) -> c.businessContact() != null && c.businessContact().email() != null && value.equalsIgnoreCase(c.businessContact().email());

    public ContactManagerRepository(String path) {
        if (db == null) {
            this.db = new ContactManagerDB(path);
        }
    }
    public List<ContactRecord> getAllContacts(ContactManagementUser user) {
        Optional<String> addressBook = db.getAddressBook(user);
        Optional o = unmarshal(addressBook, ContactRecord[].class);
        return o.isPresent() ? ImmutableList.copyOf((ContactRecord[])o.get()) : Collections.emptyList();
    }

    private List<ContactRecord> getOpenContacts(ContactManagementUser user) {
        Optional<String> addressBook = db.getAddressBook(user);
        Optional o = unmarshal(addressBook, ContactRecord[].class);

        return Arrays.stream((ContactRecord[])o.get())
                .collect(Collectors.toList());
    }
    public List<ContactRecord> saveContact(ContactManagementUser user, ContactRecord contact) {
        ContactRecord record = contact.defineId();
        Optional<String> json = Optional.empty();

        if (db.hasAddressBook(user)) {
            List<ContactRecord> list = getOpenContacts(user);

            if (list.stream().anyMatch(contactRecord -> record.equals(contactRecord))) {
                return getOpenContacts(user);
            }

            list.add(record);
            json = marshal(list);
        } else {
            json = marshal(Arrays.asList(record));
        }

        return saveContacts(user, json);
    }

    private List<ContactRecord> saveContacts(ContactManagementUser user, List<ContactRecord> addressBook) {
        Optional<String> json = marshal(addressBook);
        return saveContacts(user, json);
    }

    private List<ContactRecord> saveContacts(ContactManagementUser user, Optional<String> json) {
        Optional<String> stringOptional = db.saveContact(user, json.get());
        Optional<Object> objectOptional = unmarshal(stringOptional, ContactRecord[].class);


        return objectOptional.isPresent() ? ImmutableList.copyOf((ContactRecord[])objectOptional.get()) : Collections.emptyList();
    }
    public List<ContactRecord>  getContact(ContactManagementUser user, String value) {
        List<ContactRecord> addressBook = getAllContacts(user);
        return addressBook.stream()
                .filter(contactRecord -> validContact.test(value, contactRecord))
                .collect(Collectors.toList());
    }
    public List<ContactRecord>  getContactByFirstName(ContactManagementUser user, String firstName) {
        List<ContactRecord> addressBook = getAllContacts(user);
        return addressBook.stream()
                .filter(contactRecord -> validFirstName.test(firstName, contactRecord))
                .collect(Collectors.toList());
    }
    public List<ContactRecord> getContactByLastName(ContactManagementUser user, String lastName) {
        List<ContactRecord> addressBook = getAllContacts(user);
        return addressBook.stream()
                .filter(contactRecord -> validLastName.test(lastName, contactRecord))
                .collect(Collectors.toList());
    }
    public List<ContactRecord> getContactByPersonalPhone(ContactManagementUser user, String phone) {
        List<ContactRecord> addressBook = getAllContacts(user);
        return addressBook.stream()
                .filter(contactRecord -> validPersonalPhoneNumber.test(phone, contactRecord))
                .collect(Collectors.toList());
    }
    public List<ContactRecord> getContactByBusinesslPhone(ContactManagementUser user, String phone) {
        List<ContactRecord> addressBook = getAllContacts(user);
        return addressBook.stream()
                .filter(contactRecord -> validBusinessPhoneNumber.test(phone, contactRecord))
                .collect(Collectors.toList());
    }
    public List<ContactRecord> updateContact(ContactManagementUser user, int id, ContactRecord uptadedContact) {
        List<ContactRecord> addressBook = getOpenContacts(user);
        if (addressBook.removeIf(contactRecord -> contactRecord.id() == id)) {
            addressBook.add(uptadedContact);
            return saveContacts(user, addressBook);
        }
        return Collections.emptyList();
    }
    public List<ContactRecord> deleteContact(ContactManagementUser user, int id) {
        List<ContactRecord> addressBook = getOpenContacts(user);
        addressBook.removeIf(contactRecord -> contactRecord.id() == id);
        return saveContacts(user, addressBook);
    }
    public boolean deleteAddressBook(ContactManagementUser user) {
        try {
            db.resetAllAddress(user);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean resetAddressBook() {
        try {
            db.resetAllAddress();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
