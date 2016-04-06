package todo.backend.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;
import todo.backend.model.*;
import todo.backend.repository.*;
import todo.backend.repository.tuple.*;
import todo.backend.rest.dto.*;


@RestController
@RequestMapping("/api/")
public class TodoApi {

    private final Logger log = LoggerFactory.getLogger(TodoApi.class);

    @Inject
    private TodoRepository todoRepository;

    @Inject
    private UserRepository userRepository;

    @RequestMapping(value = "/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<FindTodoResponse>> findTodo(@Valid @RequestBody FindTodoRequest request) throws URISyntaxException {
        log.debug("GET /todos {}", request);
        List<Todo> result = todoRepository.findAllPaged(request.getDrop(), request.getTake());
        return ResponseEntity.ok().body(result.stream().map(this::convertToFindTodoResponse).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<ReadTodoResponse> readTodo(@PathVariable Long id) throws URISyntaxException {
        log.debug("GET /todo/{}", id);
        Optional<Todo> result = Optional.ofNullable(todoRepository.findOne(id));
        if (result.isPresent()) {
            return ResponseEntity.ok().body(convertToReadTodoResponse(result.get()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<CreateTodoResponse> createTodo(@Valid @RequestBody CreateTodoRequest request) throws URISyntaxException {
        log.debug("POST /todo {}", request);
        Todo todo = convertToTodo(request);
        Todo result = todoRepository.save(todo);
        return ResponseEntity.created(new URI("/todo/" + result.getId())).body(convertToCreateTodoResponse(result));
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<UpdateTodoResponse> updateTodo(@PathVariable Long id, @Valid @RequestBody RestUpdateTodoRequest request) throws URISyntaxException {
        log.debug("PUT /todo/{} {}", id, request);
        Todo todo = convertToTodo(id, request);
        Todo result = todoRepository.save(todo);
        return ResponseEntity.ok().body(convertToUpdateTodoResponse(result));
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) throws URISyntaxException {
        log.debug("DELETE /todo/{}", id);
        todoRepository.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<TodosResponse>> todos() throws URISyntaxException {
        log.debug("GET /todos");
        List<Todo> result = todoRepository.todos(request.getPrincipalId());
        return ResponseEntity.ok().body(result.stream().map(this::convertToTodosResponse).collect(Collectors.toList()));
    }

    private FindTodoResponse convertToFindTodoResponse(Todo model) {
        FindTodoResponse dto = new FindTodoResponse();
        dto.setId(model.getId());
        dto.setUserId(model.getUser().getId());
        dto.setTask(model.getTask());
        dto.setDate(model.getDate());
        return dto;
    }

    private ReadTodoResponse convertToReadTodoResponse(Todo model) {
        ReadTodoResponse dto = new ReadTodoResponse();
        dto.setId(model.getId());
        dto.setUserId(model.getUser().getId());
        dto.setTask(model.getTask());
        dto.setDate(model.getDate());
        return dto;
    }

    private Todo convertToTodo(CreateTodoRequest dto) {
        Todo todo = new Todo();
        User user = userRepository.findOne(dto.getUserId());
        todo.setUser(user);
        todo.setTask(dto.getTask());
        todo.setDate(dto.getDate());
        return todo;
    }

    private CreateTodoResponse convertToCreateTodoResponse(Todo model) {
        CreateTodoResponse dto = new CreateTodoResponse();
        dto.setId(model.getId());
        dto.setUserId(model.getUser().getId());
        dto.setTask(model.getTask());
        dto.setDate(model.getDate());
        return dto;
    }

    private Todo convertToTodo(Long id, RestUpdateTodoRequest dto) {
        Todo todo = new Todo();
        todo.setId(id);
        todo.setTask(dto.getTask());
        todo.setDate(dto.getDate());
        return todo;
    }

    private UpdateTodoResponse convertToUpdateTodoResponse(Todo model) {
        UpdateTodoResponse dto = new UpdateTodoResponse();
        dto.setId(model.getId());
        dto.setUserId(model.getUser().getId());
        dto.setTask(model.getTask());
        dto.setDate(model.getDate());
        return dto;
    }

    private TodosResponse convertToTodosResponse(Todo model) {
        TodosResponse dto = new TodosResponse();
        dto.setId(model.getId());
        dto.setUserId(model.getUser().getId());
        dto.setTask(model.getTask());
        dto.setDate(model.getDate());
        return dto;
    }
}
