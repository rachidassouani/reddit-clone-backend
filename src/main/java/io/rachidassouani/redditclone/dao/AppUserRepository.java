package io.rachidassouani.redditclone.dao;

import io.rachidassouani.redditclone.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Query("UPDATE AppUser au set au.enabled = TRUE WHERE au.email = ?1")
    @Modifying
    int enableAppUser(String email);
}
