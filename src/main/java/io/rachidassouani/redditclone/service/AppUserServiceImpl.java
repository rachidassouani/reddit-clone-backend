package io.rachidassouani.redditclone.service;

import io.rachidassouani.redditclone.dao.AppUserRepository;
import io.rachidassouani.redditclone.exception.UserExistsException;
import io.rachidassouani.redditclone.model.AppUser;
import io.rachidassouani.redditclone.model.VerificationToken;
import io.rachidassouani.redditclone.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenService verificationTokenService;

    @Override
    public String save(AppUser appUser) throws UserExistsException {

        // checking if user exist
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if(userExists) {
            throw new UserExistsException("email already taken");
        }

        final String encodedPassword = passwordEncoder
                .getPasswordEncoder().encode(appUser.getPassword());

        // assigning the encoded password to the user
        appUser.setPassword(encodedPassword);

        // saving user
        appUserRepository.save(appUser);

        // generating token for the email verification
        final String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setAppUser(appUser);
        verificationToken.setCreatedAt(Instant.now());
        // token will expire in 15 min
        verificationToken.setExpiredAt(Instant.now().plusSeconds(900));
        verificationTokenService.save(verificationToken);

        return token;
    }

    @Override
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
