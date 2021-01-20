package org.ordjo.controllers;

import org.ordjo.exceptions.ImageTooSmallException;
import org.ordjo.exceptions.InvalidFileException;
import org.ordjo.model.FileInfo;
import org.ordjo.model.Interest;
import org.ordjo.model.Profile;
import org.ordjo.model.User;
import org.ordjo.service.FileService;
import org.ordjo.service.InterestService;
import org.ordjo.service.ProfileService;
import org.ordjo.service.UserService;
import org.ordjo.status.PhotoUploadStatus;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private InterestService interestService;

    @Value("${photo.upload.directory}")
    private String photoUploadDirectory;

    @Value("${photo.upload.ok}")
    private String photoStatusOK;

    @Value("${photo.upload.invalid}")
    private String photoStatusInvalid;

    @Value("${photo.upload.ioexception}")
    private String photoStatusIOException;

    @Value("${photo.upload.toosmall}")
    private String photoStatusTooSmall;

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userService.get(email);
    }

    private ModelAndView showProfile(User user) {
        ModelAndView modelAndView = new ModelAndView();

        if (user == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        Profile profile = profileService.getUserProfile(user);

        if (profile == null) {
            profile = new Profile();
            profile.setUser(user);
            profileService.save(profile);
        }

        Profile webProfile = new Profile();
        webProfile.safeCopyFrom(profile);

        modelAndView.getModel().put("profile", webProfile);
        modelAndView.getModel().put("userId", user.getId());
        modelAndView.setViewName("app.profile");
        return modelAndView;
    }

    @RequestMapping("/profile")
    public ModelAndView showProfile() {
        User user = getUser();
        ModelAndView modelAndView = showProfile(user);
        modelAndView.getModel().put("ownProfile", true);
        return modelAndView;
    }

    @RequestMapping("/profile/{id}")
    public ModelAndView showProfile(@PathVariable("id") Long id) {
        User user = userService.get(id);
        ModelAndView modelAndView = showProfile(user);

        modelAndView.getModel().put("ownProfile", false);
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
    @ResponseBody //Return data in JSON format
    public ResponseEntity<PhotoUploadStatus> handlePhotoUploads(@RequestParam("file") MultipartFile file) {

        User user = getUser();
        Profile profile = profileService.getUserProfile(user);
        String filePrefix = "p" + user.getId();
        Path oldPhotoPath = profile.getPhoto(photoUploadDirectory);

        PhotoUploadStatus status = new PhotoUploadStatus(photoStatusOK);

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
            status.setMessage(photoStatusInvalid);
        } catch (IOException e) {
            status.setMessage(photoStatusIOException);
        } catch (ImageTooSmallException e) {
            status.setMessage(photoStatusTooSmall);
        }

        return new ResponseEntity(status, HttpStatus.OK);
    }

    @RequestMapping(value = "/profilephoto/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> servePhoto(
            @PathVariable("id") Long id
    ) throws IOException {
        User user = userService.get(id);
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

    @RequestMapping(value = "/save-interest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveInterest(
            @RequestParam("name") String interestName
    ) {
        User user = getUser();
        Profile profile = profileService.getUserProfile(user);

        String cleanedInterestName = htmlPolicy.sanitize(interestName);
        Interest interest = interestService.createIfNotExist(cleanedInterestName);
        profile.addInterest(interest);
        profileService.save(profile);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-interest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> deleteInterest(
            @RequestParam("name") String interestName
    ) {
        User user = getUser();
        Profile profile = profileService.getUserProfile(user);

        profile.removeInterest(interestName);
        profileService.save(profile);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
