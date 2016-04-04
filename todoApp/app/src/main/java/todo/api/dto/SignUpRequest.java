package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class SignUpRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public SignUpRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
