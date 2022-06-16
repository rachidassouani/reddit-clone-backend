package io.rachidassouani.redditclone.controller;

import io.rachidassouani.redditclone.dto.RegistrationRequest;
import io.rachidassouani.redditclone.exception.UserExistsException;
import io.rachidassouani.redditclone.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public void register(@RequestBody RegistrationRequest registrationRequest) throws UserExistsException {
        registrationService.register(registrationRequest);
    }
}
