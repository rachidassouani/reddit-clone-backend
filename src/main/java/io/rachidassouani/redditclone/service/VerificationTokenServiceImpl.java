package io.rachidassouani.redditclone.service;

import io.rachidassouani.redditclone.dao.VerificationTokenRepository;
import io.rachidassouani.redditclone.model.VerificationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public Optional<VerificationToken> getToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return verificationTokenRepository.updateConfirmedAt(token, Instant.now());
    }
}
