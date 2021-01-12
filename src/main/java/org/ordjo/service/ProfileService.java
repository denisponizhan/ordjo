package org.ordjo.service;

import org.ordjo.model.Profile;
import org.ordjo.model.ProfileDao;
import org.ordjo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private ProfileDao profileDao;

    public void save(Profile profile) {
        profileDao.save(profile);
    }

    public Profile getUserProfile(User user) {
        return profileDao.findByUser(user);
    }
}
