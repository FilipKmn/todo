package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class RefreshTokenResponse {

    private final String accessToken;
    private final String refreshToken;
    private final Long userId;
    private final String userFirstName;
    private final String userLastName;
    private final UserRole userRole;
    private final String userEmail;

    public RefreshTokenResponse(String accessToken, String refreshToken, Long userId, String userFirstName, String userLastName, UserRole userRole, String userEmail) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userRole = userRole;
        this.userEmail = userEmail;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getUserEmail() {
        return userEmail;
    }

}
