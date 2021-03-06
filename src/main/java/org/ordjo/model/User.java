package org.ordjo.model;

import org.hibernate.annotations.GenericGenerator;
import org.ordjo.validation.PasswordMatch;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
@PasswordMatch(message = "{register.repeatpassword.mismatch}")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "email", unique = true)
    @NotBlank(message = "{register.email.invalid}")
    @Email(message = "{register.email.invalid}")
    private String email;

    @Transient
    @Size(min = 5, max = 15, message = "{register.password.size}")
    private String plainPassword;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @Transient
    private String repeatPassword;

    @Column(name = "role", length = 20)
    private String role;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.plainPassword = password;
        this.repeatPassword = password;
        this.enabled = true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.password = new BCryptPasswordEncoder().encode(plainPassword);
        this.plainPassword = plainPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
