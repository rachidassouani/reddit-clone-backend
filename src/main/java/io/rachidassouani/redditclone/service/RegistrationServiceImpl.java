package io.rachidassouani.redditclone.service;

import io.rachidassouani.redditclone.dto.RegistrationRequest;
import io.rachidassouani.redditclone.exception.EmailAlreadyConfirmedException;
import io.rachidassouani.redditclone.exception.TokenExpiredException;
import io.rachidassouani.redditclone.exception.TokenNotFoundException;
import io.rachidassouani.redditclone.exception.UserExistsException;
import io.rachidassouani.redditclone.model.AppUser;
import io.rachidassouani.redditclone.model.VerificationToken;
import io.rachidassouani.redditclone.util.Constant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AppUserService appUserService;
    private final MailService mailService;
    private final VerificationTokenService verificationTokenService;

    @Override
    public String register(RegistrationRequest registerRequest) throws UserExistsException {

        AppUser user = new AppUser();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setCreatedAt(Instant.now());
        user.setEnabled(false);

        // saving user
        String token = appUserService.save(user);

        // sending mail notification
        String link = Constant.NOTIFICATION_EMAIL_TOKEN_LINK+token;
        mailService.sendMail(user.getEmail(), buildEmail(user.getUsername(), link));

        return token;
    }

    @Override
    public String confirmToken(String token) throws TokenNotFoundException, TokenExpiredException, EmailAlreadyConfirmedException {

        VerificationToken verificationToken = verificationTokenService.getToken(token)
                .orElseThrow(()-> new TokenNotFoundException("Token not found"));

        if (verificationToken.getConfirmedAt() != null) {
            throw new EmailAlreadyConfirmedException("email already confirmed");
        }

        if (verificationToken.getExpiredAt().isBefore(Instant.now())) {
            throw new TokenExpiredException("token expired");
        }

        verificationTokenService.setConfirmedAt(token);

        // enabling the user
        appUserService.enableAppUser(verificationToken.getAppUser().getEmail());

        return "Confirmed";
    }

    private String buildEmail(String name, String link) {
        String body =  "<div>\n" +
                "    <p>Hi "+name+"</p>\n" +
                "    <p>Thank you for registering. Please click on the below link to activate your account: </p>\n" +
                "    <a href=\""+link+"\">Activate Now</a>\n" +
                "    <p>Please note that link will expire in 15 minutes.</p>\n" +
                "</div>";
        return body;
    }
}
