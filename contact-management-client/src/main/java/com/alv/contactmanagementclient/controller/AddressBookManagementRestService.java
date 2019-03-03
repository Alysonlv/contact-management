package com.alv.contactmanagementclient.controller;

import com.alv.contactmanagementclient.service.domain.CreateContactRequest;
import com.alv.contactmanagementclient.service.domain.UpdateContactRequest;
import com.contact.management.domain.to.utils.ContactMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/*
 * Created by alysonlv - 2019-03-02
 */
@RestController
public class AddressBookManagementRestService extends AddressBookManagementServiceTemplate {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/addressbook/{username}", produces = "application/json")
    public String getAddressBook(@PathVariable String username) {
        return ContactMarshaller.marshal(getService().getAddressBook(buildUser(username))).get();
    }

    @GetMapping(value = "/addressbook/{username}/{value}", produces = "application/json")
    public String getContact(@PathVariable String username, @PathVariable String value) {
        return ContactMarshaller.marshal(getService().getContact(buildUser(username), value)).get();
    }

    @GetMapping(value = "/addressbook/{username}/firstName={value}", produces = "application/json")
    public String getContactByName(@PathVariable String username, @PathVariable String value) {
        return ContactMarshaller.marshal(getService().getContactByFirstName(buildUser(username), value)).get();
    }

    @GetMapping(value = "/addressbook/{username}/lastName={value}", produces = "application/json")
    public String getContactByLastName(@PathVariable String username, @PathVariable String value) {
        return ContactMarshaller.marshal(getService().getContactByLastName(buildUser(username), value)).get();
    }

    @GetMapping(value = "/addressbook/{username}/personalPhone={value}", produces = "application/json")
    public String getContactByPersonalPhone(@PathVariable String username, @PathVariable String value) {
        return ContactMarshaller.marshal(getService().getContactByPersonalPhone(buildUser(username), value)).get();
    }

    @GetMapping(value = "/addressbook/{username}/businessPhone={value}", produces = "application/json")
    public String getContactByBusinessPhone(@PathVariable String username, @PathVariable String value) {
        return ContactMarshaller.marshal(getService().getContactByBusinesslPhone(buildUser(username), value)).get();
    }

    @PostMapping(value = "/addressbook/{username}", consumes = "application/json", produces = "application/json")
    public String createContac(@PathVariable String username, @RequestBody CreateContactRequest request) {
        return ContactMarshaller.marshal(getService().saveContact(buildUser(username), request)).get();
    }

    @PutMapping(value = "/addressbook/{username}", produces = "application/json")
    public String updateContact(@PathVariable String username, @RequestBody UpdateContactRequest request) {
        return ContactMarshaller.marshal(getService().updateContact(buildUser(username), request)).get();
    }

    @DeleteMapping(value = "/addressbook/{username}", produces = "application/json")
    public String deleteAddressBook(@PathVariable String username) {
        return ContactMarshaller.marshal(getService().deleteAddressBook(buildUser(username))).get();
    }

    @DeleteMapping(value = "/addressbook/{username}/{id}", produces = "application/json")
    public String deleteContact(@PathVariable String username, @PathVariable int id) {
        return ContactMarshaller.marshal(getService().deleteContact(buildUser(username), id)).get();
    }
}
