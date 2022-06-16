package io.rachidassouani.redditclone.exception;

public class UserExistsException extends Exception {
    public UserExistsException(String message) {
        super(message);
    }
}
