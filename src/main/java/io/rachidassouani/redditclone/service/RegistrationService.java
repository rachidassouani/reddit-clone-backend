package io.rachidassouani.redditclone.service;

import io.rachidassouani.redditclone.dto.RegistrationRequest;
import io.rachidassouani.redditclone.exception.UserExistsException;

public interface RegistrationService {
    String register(RegistrationRequest registerRequest) throws UserExistsException;
}
