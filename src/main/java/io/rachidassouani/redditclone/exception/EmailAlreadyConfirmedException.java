package io.rachidassouani.redditclone.exception;

public class EmailAlreadyConfirmedException extends Exception {
    public EmailAlreadyConfirmedException(String message) {
        super(message);
    }
}
