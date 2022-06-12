package io.rachidassouani.redditclone.dao;

import io.rachidassouani.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
