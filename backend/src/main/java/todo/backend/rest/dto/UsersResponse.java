package todo.backend.rest.dto;

import java.io.Serializable;
import java.time.*;
import java.util.*;

import javax.validation.constraints.*;

import todo.backend.model.enumeration.*;


public class UsersResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1, max = 40)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 60)
    private String lastName;

    @NotNull
    private UserRole role;

    @NotNull
    @Size(min = 6, max = 128)
    @Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$")
    private String email;

    @NotNull
    @Size(min = 6, max = 128)
    private String passwordHash;

    @Size(min = 64, max = 64)
    private String emailVerificationCode;

    private ZonedDateTime emailVerificationCodeTimestamp;

    @NotNull
    private Boolean emailVerified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    public ZonedDateTime getEmailVerificationCodeTimestamp() {
        return emailVerificationCodeTimestamp;
    }

    public void setEmailVerificationCodeTimestamp(ZonedDateTime emailVerificationCodeTimestamp) {
        this.emailVerificationCodeTimestamp = emailVerificationCodeTimestamp;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsersResponse other = (UsersResponse) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (passwordHash == null) {
            if (other.passwordHash != null)
                return false;
        } else if (!passwordHash.equals(other.passwordHash))
            return false;
        if (emailVerificationCode == null) {
            if (other.emailVerificationCode != null)
                return false;
        } else if (!emailVerificationCode.equals(other.emailVerificationCode))
            return false;
        if (emailVerificationCodeTimestamp == null) {
            if (other.emailVerificationCodeTimestamp != null)
                return false;
        } else if (!emailVerificationCodeTimestamp.equals(other.emailVerificationCodeTimestamp))
            return false;
        if (emailVerified == null) {
            if (other.emailVerified != null)
                return false;
        } else if (!emailVerified.equals(other.emailVerified))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((passwordHash == null) ? 0 : passwordHash.hashCode());
        result = prime * result + ((emailVerificationCode == null) ? 0 : emailVerificationCode.hashCode());
        result = prime * result + ((emailVerificationCodeTimestamp == null) ? 0 : emailVerificationCodeTimestamp.hashCode());
        result = prime * result + ((emailVerified == null) ? 0 : emailVerified.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("UsersResponse [id=%s, firstName=%s, lastName=%s, role=%s, email=%s, emailVerificationCodeTimestamp=%s, emailVerified=%s]", id, firstName, lastName, role, email,
                emailVerificationCodeTimestamp, emailVerified);
    }

}
