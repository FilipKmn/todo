package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class VerifyEmailResponse {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final UserRole role;
    private final String email;

    public VerifyEmailResponse(Long id, String firstName, String lastName, UserRole role, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
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

}
