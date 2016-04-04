package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class ResetPasswordRequest {

    private final String email;

    public ResetPasswordRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
