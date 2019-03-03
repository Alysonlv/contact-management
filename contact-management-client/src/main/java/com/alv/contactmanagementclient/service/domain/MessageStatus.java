package com.alv.contactmanagementclient.service.domain;
/*
 * Created by alysonlv - 2019-03-02
 */

public class MessageStatus {

    private MessageStatusCode statusCode;

    private String message;

    private static final String SUCCESS_MESSAGE = "Requisition completed successfully.";

    private static final String ERROR_MESSAGE = "Could not complete request.";

    public MessageStatus(MessageStatusCode statusCode) {
        this.statusCode = statusCode;
        this.message = statusCode == MessageStatusCode.OK ? SUCCESS_MESSAGE : ERROR_MESSAGE;
    }


    public MessageStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(MessageStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static enum MessageStatusCode {
        OK,
        NOK;
    }
}
