package io.rachidassouani.redditclone.dao;

import io.rachidassouani.redditclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
