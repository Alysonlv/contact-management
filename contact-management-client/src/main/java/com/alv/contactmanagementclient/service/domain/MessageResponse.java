package com.alv.contactmanagementclient.service.domain;
/*
 * Created by alysonlv - 2019-03-02
 */


import com.contact.management.domain.to.ContactManagementUser;
import com.contact.management.domain.to.ContactRecord;

import java.util.List;

public class MessageResponse {

    private MessageStatus status;

    private List<ContactRecord> contactRecords;

    private ContactManagementUser user;

    public ContactManagementUser getUser() {
        return user;
    }

    public void setUser(ContactManagementUser user) {
        this.user = user;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public List<ContactRecord> getContactRecords() {
        return contactRecords;
    }

    public void setContactRecords(List<ContactRecord> contactRecords) {
        this.contactRecords = contactRecords;
    }
}
