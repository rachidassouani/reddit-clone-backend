package io.rachidassouani.redditclone.service;

import io.rachidassouani.redditclone.exception.UserExistsException;
import io.rachidassouani.redditclone.model.AppUser;

public interface AppUserService {
    String save(AppUser appUser) throws UserExistsException;
}
