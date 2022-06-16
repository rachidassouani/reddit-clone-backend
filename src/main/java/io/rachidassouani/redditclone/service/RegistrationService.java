package io.rachidassouani.redditclone.service;

import io.rachidassouani.redditclone.dto.RegistrationRequest;
import io.rachidassouani.redditclone.exception.EmailAlreadyConfirmedException;
import io.rachidassouani.redditclone.exception.TokenExpiredException;
import io.rachidassouani.redditclone.exception.TokenNotFoundException;
import io.rachidassouani.redditclone.exception.UserExistsException;

public interface RegistrationService {

    String register(RegistrationRequest registerRequest) throws UserExistsException;
    String confirmToken(String token) throws TokenNotFoundException, TokenExpiredException, EmailAlreadyConfirmedException;
}
