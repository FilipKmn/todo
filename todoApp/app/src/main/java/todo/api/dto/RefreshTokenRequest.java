package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class RefreshTokenRequest {

    private final String refreshToken;

    public RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

}
