package todo.backend.repository;

import java.time.*;
import java.util.*;

import todo.backend.model.*;
import todo.backend.model.enumeration.*;
import todo.backend.repository.tuple.*;


public interface UserRepositoryCustom {

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    List<User> findByRole(UserRole role);

    Optional<User> findByEmail(String email);

    List<User> findByPasswordHash(String passwordHash);

    Optional<User> findByEmailVerificationCode(Optional<String> emailVerificationCode);

    Optional<User> findByEmailVerificationCodeMandatory(String emailVerificationCode);

    List<User> findByEmailVerificationCodeTimestamp(Optional<ZonedDateTime> emailVerificationCodeTimestamp);

    List<User> findByEmailVerificationCodeTimestampMandatory(ZonedDateTime emailVerificationCodeTimestamp);

    List<User> findByEmailVerified(Boolean emailVerified);

    List<User> users();

}
