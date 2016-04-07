package todo.backend.repository.impl;

import java.time.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import todo.backend.model.*;

import todo.backend.repository.UserSessionRepositoryCustom;
import todo.backend.repository.tuple.*;

import com.querydsl.jpa.JPQLQueryFactory;


public class UserSessionRepositoryImpl implements UserSessionRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(UserSessionRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public Optional<UserSessionUserTuple> findByIdWithUser(Long id) {
        log.trace(".findByIdWithUser(id: {})", id);
        QUser user = new QUser("user");
        QUserSession userSession = new QUserSession("userSession");
        return Optional.ofNullable(factory.select(userSession, user).from(userSession).innerJoin(userSession.user, user).where(userSession.id.eq(id)).fetchOne())
                .map(t -> new UserSessionUserTuple(t.get(userSession), t.get(user)));
    }

    @Override
    public List<UserSession> findByUser(Long userId) {
        log.trace(".findByUser(userId: {})", userId);
        QUserSession userSession = new QUserSession("userSession");
        return factory.select(userSession).from(userSession).where(userSession.user.id.eq(userId)).fetch();
    }

    @Override
    public Optional<UserSession> findByAccessToken(String accessToken) {
        log.trace(".findByAccessToken(accessToken)");
        QUserSession userSession = new QUserSession("userSession");
        return Optional.ofNullable(factory.select(userSession).from(userSession).where(userSession.accessToken.eq(accessToken)).fetchOne());
    }

    @Override
    public List<UserSession> findByAccessTokenExpires(ZonedDateTime accessTokenExpires) {
        log.trace(".findByAccessTokenExpires(accessTokenExpires: {})", accessTokenExpires);
        QUserSession userSession = new QUserSession("userSession");
        return factory.select(userSession).from(userSession).where(userSession.accessTokenExpires.eq(accessTokenExpires)).fetch();
    }

    @Override
    public Optional<UserSession> findByRefreshToken(String refreshToken) {
        log.trace(".findByRefreshToken(refreshToken)");
        QUserSession userSession = new QUserSession("userSession");
        return Optional.ofNullable(factory.select(userSession).from(userSession).where(userSession.refreshToken.eq(refreshToken)).fetchOne());
    }

    @Override
    public List<UserSession> findByRefreshTokenExpires(ZonedDateTime refreshTokenExpires) {
        log.trace(".findByRefreshTokenExpires(refreshTokenExpires: {})", refreshTokenExpires);
        QUserSession userSession = new QUserSession("userSession");
        return factory.select(userSession).from(userSession).where(userSession.refreshTokenExpires.eq(refreshTokenExpires)).fetch();
    }

}
