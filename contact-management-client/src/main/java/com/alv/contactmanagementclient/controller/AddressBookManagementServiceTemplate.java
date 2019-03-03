package com.alv.contactmanagementclient.controller;
/*
 * Created by alysonlv - 2019-03-02
 */

import com.alv.contactmanagementclient.service.AddressBookService;
import com.contact.management.domain.to.ContactManagementUser;
import com.contact.management.domain.to.ImmutableContactManagementUser;

public class AddressBookManagementServiceTemplate {

    private String path = "contact-management-repository/src/main/resources/addresses/";

    public AddressBookService getService() {
        return new AddressBookService(path);
    }

    public ContactManagementUser buildUser(String user) {
        return ImmutableContactManagementUser.builder().username(user).build();
    }

}
