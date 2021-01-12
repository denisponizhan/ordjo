package org.ordjo.controllers;

import org.checkerframework.checker.units.qual.A;
import org.ordjo.model.Profile;
import org.ordjo.model.User;
import org.ordjo.service.ProfileService;
import org.ordjo.service.UserService;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PolicyFactory htmlPolicy;

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userService.get(email);
    }

    @RequestMapping("/profile")
    public ModelAndView showProfile(ModelAndView modelAndView) {

        User user = getUser();
        Profile profile = profileService.getUserProfile(user);

        if (profile == null) {
            profile = new Profile();
            profile.setUser(user);
            profileService.save(profile);
        }

        Profile webProfile = new Profile();
        webProfile.safeCopyFrom(profile);

        modelAndView.getModel().put("profile", webProfile);

        modelAndView.setViewName("app.profile");
        return modelAndView;
    }

    @RequestMapping(value = "/edit-profile-text", method = RequestMethod.GET)
    public ModelAndView editProfileText(ModelAndView modelAndView) {
        User user = getUser();
        Profile profile = profileService.getUserProfile(user);

        Profile webProfile = new Profile();
        webProfile.safeCopyFrom(profile);

        modelAndView.getModel().put("profile", webProfile);
        modelAndView.setViewName("app.editprofileabout");

        return modelAndView;
    }

    @RequestMapping(value = "/edit-profile-text", method = RequestMethod.POST)
    public ModelAndView editProfileText(
            ModelAndView modelAndView,
            @Valid Profile webProfile,
            BindingResult result) {

        modelAndView.setViewName("app.editprofileabout");

        User user = getUser();
        Profile profile = profileService.getUserProfile(user);
        profile.safeMergeFrom(webProfile, htmlPolicy);

        if (!result.hasErrors()) {
            profileService.save(profile);
            modelAndView.setViewName("redirect:/profile");
        }

        return modelAndView;
    }
}
