package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class VerifyEmailRequest {

    private final String emailVerificationCode;

    public VerifyEmailRequest(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

}
