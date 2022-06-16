package io.rachidassouani.redditclone.dao;

import io.rachidassouani.redditclone.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);

    @Query("UPDATE VerificationToken vt set vt.confirmedAt = ?2 WHERE vt.token = ?1")
    @Modifying
    int updateConfirmedAt(String token, Instant now);
}
