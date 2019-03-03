package com.alv.contactmanagementclient.service.domain;
/*
 * Created by alysonlv - 2019-03-02
 */

import com.contact.management.domain.to.ContactRecord;

public class UpdateContactRequest {

    private ContactRecord contactRecord;

    public ContactRecord getContactRecord() {
        return contactRecord;
    }

    public void setContactRecord(ContactRecord contactRecord) {
        this.contactRecord = contactRecord;
    }
}
