package io.rachidassouani.redditclone.service;

import io.rachidassouani.redditclone.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenService {
    VerificationToken save(VerificationToken verificationToken);
    Optional<VerificationToken> getToken(String token);

    int setConfirmedAt(String token);
}
