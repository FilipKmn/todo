package todo.backend.repository;

import java.time.*;
import java.util.*;

import todo.backend.model.*;
import todo.backend.model.enumeration.*;
import todo.backend.repository.tuple.*;


public interface UserSessionRepositoryCustom {

    Optional<UserSessionUserTuple> findByIdWithUser(Long id);

    List<UserSession> findByUser(Long userId);

    Optional<UserSession> findByAccessToken(String accessToken);

    List<UserSession> findByAccessTokenExpires(ZonedDateTime accessTokenExpires);

    Optional<UserSession> findByRefreshToken(String refreshToken);

    List<UserSession> findByRefreshTokenExpires(ZonedDateTime refreshTokenExpires);

}
