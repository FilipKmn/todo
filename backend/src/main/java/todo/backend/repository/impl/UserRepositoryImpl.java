package todo.backend.repository.impl;

import static com.querydsl.core.group.GroupBy.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import todo.backend.model.*;
import todo.backend.model.enumeration.*;
import todo.backend.repository.UserRepositoryCustom;
import todo.backend.repository.tuple.*;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQueryFactory;


public class UserRepositoryImpl implements UserRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<User> findByFirstName(String firstName) {
        log.trace(".findByFirstName(firstName: {})", firstName);
        QUser user = new QUser("user");
        return factory.select(user).from(user).where(user.firstName.eq(firstName)).fetch();
    }

    @Override
    public List<User> findByLastName(String lastName) {
        log.trace(".findByLastName(lastName: {})", lastName);
        QUser user = new QUser("user");
        return factory.select(user).from(user).where(user.lastName.eq(lastName)).fetch();
    }

    @Override
    public List<User> findByRole(UserRole role) {
        log.trace(".findByRole(role: {})", role);
        QUser user = new QUser("user");
        return factory.select(user).from(user).where(user.role.eq(role)).fetch();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.trace(".findByEmail(email: {})", email);
        QUser user = new QUser("user");
        return Optional.ofNullable(factory.select(user).from(user).where(user.email.eq(email)).fetchOne());
    }

    @Override
    public List<User> findByPasswordHash(String passwordHash) {
        log.trace(".findByPasswordHash(passwordHash)");
        QUser user = new QUser("user");
        return factory.select(user).from(user).where(user.passwordHash.eq(passwordHash)).fetch();
    }

    @Override
    public Optional<User> findByEmailVerificationCode(Optional<String> emailVerificationCode) {
        log.trace(".findByEmailVerificationCode(emailVerificationCode)");
        QUser user = new QUser("user");
        return Optional.ofNullable(factory.select(user).from(user).where(emailVerificationCode.isPresent() ? user.emailVerificationCode.eq(emailVerificationCode.get()) : null).fetchOne());
    }

    @Override
    public Optional<User> findByEmailVerificationCodeMandatory(String emailVerificationCode) {
        log.trace(".findByEmailVerificationCodeMandatory(emailVerificationCode)");
        QUser user = new QUser("user");
        return Optional.ofNullable(factory.select(user).from(user).where(user.emailVerificationCode.eq(emailVerificationCode)).fetchOne());
    }

    @Override
    public List<User> findByEmailVerificationCodeTimestamp(Optional<ZonedDateTime> emailVerificationCodeTimestamp) {
        log.trace(".findByEmailVerificationCodeTimestamp(emailVerificationCodeTimestamp: {})", emailVerificationCodeTimestamp);
        QUser user = new QUser("user");
        return factory.select(user).from(user).where(emailVerificationCodeTimestamp.isPresent() ? user.emailVerificationCodeTimestamp.eq(emailVerificationCodeTimestamp.get()) : null).fetch();
    }

    @Override
    public List<User> findByEmailVerificationCodeTimestampMandatory(ZonedDateTime emailVerificationCodeTimestamp) {
        log.trace(".findByEmailVerificationCodeTimestampMandatory(emailVerificationCodeTimestamp: {})", emailVerificationCodeTimestamp);
        QUser user = new QUser("user");
        return factory.select(user).from(user).where(user.emailVerificationCodeTimestamp.eq(emailVerificationCodeTimestamp)).fetch();
    }

    @Override
    public List<User> findByEmailVerified(Boolean emailVerified) {
        log.trace(".findByEmailVerified(emailVerified: {})", emailVerified);
        QUser user = new QUser("user");
        return factory.select(user).from(user).where(user.emailVerified.eq(emailVerified)).fetch();
    }

    @Override
    public List<User> users() {
        log.trace(".users()");
        QUser user = new QUser("user");
        return factory.select(user).from(user).fetch();
    }

}
