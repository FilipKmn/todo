package todo.backend.repository.impl;

import java.time.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import todo.backend.model.*;

import todo.backend.repository.TodoRepositoryCustom;

import com.querydsl.jpa.JPQLQueryFactory;


public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(TodoRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<Todo> findByUser(Long userId) {
        log.trace(".findByUser(userId: {})", userId);
        QTodo todo = new QTodo("todo");
        return factory.select(todo).from(todo).where(todo.user.id.eq(userId)).fetch();
    }

    @Override
    public List<Todo> findByTask(String task) {
        log.trace(".findByTask(task: {})", task);
        QTodo todo = new QTodo("todo");
        return factory.select(todo).from(todo).where(todo.task.eq(task)).fetch();
    }

    @Override
    public List<Todo> findByDate(LocalDate date) {
        log.trace(".findByDate(date: {})", date);
        QTodo todo = new QTodo("todo");
        return factory.select(todo).from(todo).where(todo.date.eq(date)).fetch();
    }

    @Override
    public List<Todo> todos(Long principalId) {
        log.trace(".todos(principalId: {})", principalId);
        QTodo todo = new QTodo("todo");
        return factory.select(todo).from(todo).where(todo.user.id.eq(principalId)).fetch();
    }

    @Override
    public List<Todo> userTodos(Long userId) {
        log.trace(".userTodos(userId: {})", userId);
        QTodo todo = new QTodo("todo");
        return factory.select(todo).from(todo).where(todo.user.id.eq(userId)).fetch();
    }

    @Override
    public List<Todo> findAllPaged(Integer drop, Integer take) {
        log.trace(".findAllPaged(drop: {}, take: {})", drop, take);
        QTodo todo = new QTodo("todo");
        return factory.select(todo).from(todo).offset(drop).limit(take).fetch();
    }

    @Override
    public List<Todo> userTodosPaged(Long userId, Integer drop, Integer take) {
        log.trace(".userTodosPaged(userId: {}, drop: {}, take: {})", userId, drop, take);
        QTodo todo = new QTodo("todo");
        return factory.select(todo).from(todo).where(todo.user.id.eq(userId)).offset(drop).limit(take).fetch();
    }

    @Override
    public Long countUserTodos(Long userId) {
        log.trace(".countUserTodos(userId: {})", userId);
        QTodo todo = new QTodo("todo");
        return factory.select(todo).from(todo).where(todo.user.id.eq(userId)).fetchCount();
    }

}
