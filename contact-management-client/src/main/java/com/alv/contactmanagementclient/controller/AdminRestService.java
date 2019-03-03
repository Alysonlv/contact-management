package com.alv.contactmanagementclient.controller;
/*
 * Created by alysonlv - 2019-03-03
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AdminRestService extends AddressBookManagementServiceTemplate {

    @Autowired
    RestTemplate restTemplate;

    @DeleteMapping(value = "/addressbook/admin/", produces = "application/json")
    public boolean deleteAddressBook() {
        return getService().resetBookAddress();
    }
}
