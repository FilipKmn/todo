package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class UsersResponse {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final UserRole role;
    private final String email;
    private final String passwordHash;
    private final String emailVerificationCode;
    private final Date emailVerificationCodeTimestamp;
    private final Boolean emailVerified;

    public UsersResponse(Long id, String firstName, String lastName, UserRole role, String email, String passwordHash, String emailVerificationCode, Date emailVerificationCodeTimestamp,
            Boolean emailVerified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.passwordHash = passwordHash;
        this.emailVerificationCode = emailVerificationCode;
        this.emailVerificationCodeTimestamp = emailVerificationCodeTimestamp;
        this.emailVerified = emailVerified;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public Date getEmailVerificationCodeTimestamp() {
        return emailVerificationCodeTimestamp;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

}
