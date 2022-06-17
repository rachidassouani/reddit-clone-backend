package io.rachidassouani.redditclone.controller;

import io.rachidassouani.redditclone.dto.RegistrationRequest;
import io.rachidassouani.redditclone.exception.EmailAlreadyConfirmedException;
import io.rachidassouani.redditclone.exception.TokenExpiredException;
import io.rachidassouani.redditclone.exception.TokenNotFoundException;
import io.rachidassouani.redditclone.exception.UserExistsException;
import io.rachidassouani.redditclone.service.RegistrationService;
import io.rachidassouani.redditclone.util.Constant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest) throws UserExistsException {
        registrationService.register(registrationRequest);
        return new ResponseEntity<>(Constant.USER_CREATED_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) throws TokenExpiredException, EmailAlreadyConfirmedException, TokenNotFoundException {
        return registrationService.confirmToken(token);
    }
}
