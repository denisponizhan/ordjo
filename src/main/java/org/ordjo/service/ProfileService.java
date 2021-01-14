package org.ordjo.service;

import org.ordjo.model.Profile;
import org.ordjo.model.ProfileDao;
import org.ordjo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private ProfileDao profileDao;

    @PreAuthorize("isAuthenticated()")
    public void save(Profile profile) {
        profileDao.save(profile);
    }

    @PreAuthorize("isAuthenticated()")
    public Profile getUserProfile(User user) {
        return profileDao.findByUser(user);
    }
}
