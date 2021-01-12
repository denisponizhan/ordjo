package org.ordjo.model;

import org.hibernate.annotations.GenericGenerator;
import org.owasp.html.PolicyFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "about", length = 5000)
    @Size(max = 5000, message = "{edit.profile.about.size}")
    private String about;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    //wtf?
    public void safeCopyFrom(Profile other) {
        if (other.about != null) {
            this.about = other.about;
        }
    }

    public void safeMergeFrom(Profile webProfile, PolicyFactory htmlPolicy) {
        if (webProfile.about != null) {
            this.about = htmlPolicy.sanitize(webProfile.about);
        }
    }
}
