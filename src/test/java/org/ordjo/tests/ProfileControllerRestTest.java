package org.ordjo.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class ProfileControllerRestTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private InterestService interestService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username = "u1@mail.com")
    public void testSaveAndDeleteInterest() throws Exception {
        String interestText = "some interest_here";
        mockMvc.perform(post("/save-interest").param("name", interestText))
            .andExpect(status().isOk());

        Interest interest = interestService.get(interestText);

        assertNotNull("Interest should exist", interest);
        assertEquals("Retrieved interest text should match", interestText, interest.getName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user =  userService.get(email);
        Profile profile = profileService.getUserProfile(user);

        assertTrue("Profile should contain interest",
                profile.getInterests().contains(new Interest(interestText)));

        mockMvc.perform(post("/delete-interest").param("name", interestText))
                .andExpect(status().isOk());

        profile = profileService.getUserProfile(user);
        assertFalse("Profile should not contain interest",
                profile.getInterests().contains(new Interest(interestText)));
    }
}
