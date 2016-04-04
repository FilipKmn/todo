package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class AuthenticateRequest {

    private final String accessToken;

    public AuthenticateRequest(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
