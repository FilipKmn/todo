package todo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import todo.backend.model.Todo;


public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {

}
