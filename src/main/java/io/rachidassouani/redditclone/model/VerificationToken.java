package io.rachidassouani.redditclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @ManyToOne
    private AppUser user;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant expiredAt;

    private Instant confirmedAt;

    public VerificationToken(String token, AppUser user, Instant createdAt, Instant expiredAt, Instant confirmedAt) {
        this.token = token;
        this.user = user;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.confirmedAt = confirmedAt;
    }
}
