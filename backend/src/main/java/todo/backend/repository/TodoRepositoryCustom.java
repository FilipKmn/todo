package todo.backend.repository;

import java.time.*;
import java.util.*;

import todo.backend.model.*;


public interface TodoRepositoryCustom {

    List<Todo> findByUser(Long userId);

    List<Todo> findByTask(String task);

    List<Todo> findByDate(LocalDate date);

    List<Todo> todos(Long principalId);

    List<Todo> userTodos(Long userId);

    List<Todo> findAllPaged(Integer drop, Integer take);

    List<Todo> userTodosPaged(Long userId, Integer drop, Integer take);

    Long countUserTodos(Long userId);

}
