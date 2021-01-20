package org.ordjo.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ordjo.model.Interest;
import org.ordjo.model.Profile;
import org.ordjo.model.User;
import org.ordjo.service.InterestService;
import org.ordjo.service.ProfileService;
import org.ordjo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class ProfileTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private InterestService interestService;

    private User[] users = {
            new User("u1@example.com", "111111"),
            new User("u2@example.com", "111111"),
            new User("u3@example.com", "111111")
    };

    private String[][] interests = {
            {"music", "guitar", "plants"},
            {"music", "music", "cars"},
            {"football", "cars", "milk"},
    };

    @Test
    public void testInterests() {
//        for (int i = 0; i < users.length; i++) {
//            User user = users[i];
//            String[] interestsArray = interests[i];
//
//            userService.register(user);
//            HashSet<Interest> interestsSet = new HashSet<>();
//
//            for (String interestText: interestsArray) {
//                 Interest interest = interestService.createIfNotExist(interestText);
//                 interestsSet.add(interest);
//
//                 assertNotNull("Interest should not be null", interest);
//                 assertNotNull("Interest should have an Id", interest.getId());
//                 assertEquals("Text should match", interestText, interest.getName());
//            }
//
//            Profile profile = new Profile();
//            profile.setInterests(interestsSet);
//            profileService.save(profile);
//
//            Profile retrievedProfile = profileService.getUserProfile(user);
//
//            assertEquals("Interest sets should match", interestsSet, retrievedProfile.getInterests());
//        }
    }
}
