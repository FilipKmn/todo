package todo.backend.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.*;


public class AuthenticateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 64, max = 64)
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AuthenticateRequest other = (AuthenticateRequest) obj;
        if (accessToken == null) {
            if (other.accessToken != null)
                return false;
        } else if (!accessToken.equals(other.accessToken))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("AuthenticateRequest []");
    }

}
