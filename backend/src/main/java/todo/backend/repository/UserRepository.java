package todo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import todo.backend.model.User;


public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
