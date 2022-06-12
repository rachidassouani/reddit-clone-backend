package io.rachidassouani.redditclone.dao;

import io.rachidassouani.redditclone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
