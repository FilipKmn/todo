package todo.backend.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.*;


public class RefreshTokenRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 64, max = 64)
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RefreshTokenRequest other = (RefreshTokenRequest) obj;
        if (refreshToken == null) {
            if (other.refreshToken != null)
                return false;
        } else if (!refreshToken.equals(other.refreshToken))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("RefreshTokenRequest []");
    }

}
