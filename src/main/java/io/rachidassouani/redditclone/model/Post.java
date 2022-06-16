package io.rachidassouani.redditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postName;
    private String url;
    private Instant createdAt;
    @Lob
    private String description;
    private int voteCount;
    @ManyToOne
    private AppUser user;
    @ManyToOne
    private Subreddit subreddit;
}
