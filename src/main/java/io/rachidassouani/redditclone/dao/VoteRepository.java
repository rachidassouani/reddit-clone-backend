package io.rachidassouani.redditclone.dao;

import io.rachidassouani.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
