package org.ordjo.controllers;

import org.ordjo.exceptions.ImageTooSmallException;
import org.ordjo.exceptions.InvalidFileException;
import org.ordjo.model.FileInfo;
import org.ordjo.model.Profile;
import org.ordjo.model.User;
import org.ordjo.service.FileService;
import org.ordjo.service.ProfileService;
import org.ordjo.service.UserService;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PolicyFactory htmlPolicy;

    @Autowired
    private FileService fileService;

    @Value("${photo.upload.directory}")
    private String photoUploadDirectory;

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

    @RequestMapping(value = "/upload-profile-photo", method = RequestMethod.POST)
    public ModelAndView handlePhotoUploads(
            ModelAndView modelAndView,
            @RequestParam("file") MultipartFile file) {

        modelAndView.setViewName("redirect:/profile");

        User user = getUser();
        Profile profile = profileService.getUserProfile(user);
        String filePrefix = "p" + user.getId();
        Path oldPhotoPath = profile.getPhoto(photoUploadDirectory);

        try {
            FileInfo photoInfo = fileService.saveImageFile(file,
                    photoUploadDirectory,
                    "photos",
                    filePrefix,
                    100,
                    100);

            profile.setPhotoDetails(photoInfo);
            profileService.save(profile);

            if (oldPhotoPath != null) {
                Files.delete(oldPhotoPath);
            }

        } catch (InvalidFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageTooSmallException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @RequestMapping(value = "/profilephoto", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> servePhoto() throws IOException {
        User user = getUser();
        Profile profile = profileService.getUserProfile(user);

        Path photoPath = Paths.get(photoUploadDirectory, "default", "avatar.jpg");

        if (profile != null && profile.getPhoto(photoUploadDirectory) != null) {
            photoPath = profile.getPhoto(photoUploadDirectory);
        }

        return ResponseEntity
                .ok()
                .contentLength(Files.size(photoPath))
                .contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(photoPath.toString())))
                .body(new InputStreamResource(Files.newInputStream(photoPath, StandardOpenOption.READ)));
    }
}
