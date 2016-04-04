package todo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import todo.backend.model.UserSession;


public interface UserSessionRepository extends JpaRepository<UserSession, Long>, UserSessionRepositoryCustom {

}
