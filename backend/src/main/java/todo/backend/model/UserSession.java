package todo.backend.model;

import java.io.Serializable;
import java.time.*;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "UserSession")
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @NotNull
    @Size(min = 64, max = 64)
    @Column(name = "accessToken")
    private String accessToken;

    @NotNull
    @Column(name = "accessTokenExpires")
    private ZonedDateTime accessTokenExpires;

    @NotNull
    @Size(min = 64, max = 64)
    @Column(name = "refreshToken")
    private String refreshToken;

    @NotNull
    @Column(name = "refreshTokenExpires")
    private ZonedDateTime refreshTokenExpires;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public ZonedDateTime getAccessTokenExpires() {
        return accessTokenExpires;
    }

    public void setAccessTokenExpires(ZonedDateTime accessTokenExpires) {
        this.accessTokenExpires = accessTokenExpires;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public ZonedDateTime getRefreshTokenExpires() {
        return refreshTokenExpires;
    }

    public void setRefreshTokenExpires(ZonedDateTime refreshTokenExpires) {
        this.refreshTokenExpires = refreshTokenExpires;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserSession other = (UserSession) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (accessToken == null) {
            if (other.accessToken != null)
                return false;
        } else if (!accessToken.equals(other.accessToken))
            return false;
        if (accessTokenExpires == null) {
            if (other.accessTokenExpires != null)
                return false;
        } else if (!accessTokenExpires.equals(other.accessTokenExpires))
            return false;
        if (refreshToken == null) {
            if (other.refreshToken != null)
                return false;
        } else if (!refreshToken.equals(other.refreshToken))
            return false;
        if (refreshTokenExpires == null) {
            if (other.refreshTokenExpires != null)
                return false;
        } else if (!refreshTokenExpires.equals(other.refreshTokenExpires))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
        result = prime * result + ((accessTokenExpires == null) ? 0 : accessTokenExpires.hashCode());
        result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
        result = prime * result + ((refreshTokenExpires == null) ? 0 : refreshTokenExpires.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("UserSession [id=%s, user=%s, accessTokenExpires=%s, refreshTokenExpires=%s]", id, user, accessTokenExpires, refreshTokenExpires);
    }

}
