package todo.backend.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import todo.backend.model.enumeration.*;


public class AuthenticateResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 64, max = 64)
    private String accessToken;

    @NotNull
    @Size(min = 64, max = 64)
    private String refreshToken;

    @NotNull
    private Long userId;

    @NotNull
    @Size(min = 1, max = 40)
    private String userFirstName;

    @NotNull
    @Size(min = 1, max = 60)
    private String userLastName;

    @NotNull
    private UserRole userRole;

    @NotNull
    @Size(min = 6, max = 128)
    @Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$")
    private String userEmail;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AuthenticateResponse other = (AuthenticateResponse) obj;
        if (accessToken == null) {
            if (other.accessToken != null)
                return false;
        } else if (!accessToken.equals(other.accessToken))
            return false;
        if (refreshToken == null) {
            if (other.refreshToken != null)
                return false;
        } else if (!refreshToken.equals(other.refreshToken))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (userFirstName == null) {
            if (other.userFirstName != null)
                return false;
        } else if (!userFirstName.equals(other.userFirstName))
            return false;
        if (userLastName == null) {
            if (other.userLastName != null)
                return false;
        } else if (!userLastName.equals(other.userLastName))
            return false;
        if (userRole == null) {
            if (other.userRole != null)
                return false;
        } else if (!userRole.equals(other.userRole))
            return false;
        if (userEmail == null) {
            if (other.userEmail != null)
                return false;
        } else if (!userEmail.equals(other.userEmail))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
        result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
        result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
        result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
        result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("AuthenticateResponse [userId=%s, userFirstName=%s, userLastName=%s, userRole=%s, userEmail=%s]", userId, userFirstName, userLastName, userRole, userEmail);
    }

}
