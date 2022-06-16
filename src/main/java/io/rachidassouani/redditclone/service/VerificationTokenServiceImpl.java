package io.rachidassouani.redditclone.service;

import io.rachidassouani.redditclone.dao.VerificationTokenRepository;
import io.rachidassouani.redditclone.model.VerificationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }
}
