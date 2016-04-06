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
public class UserApi {

    private final Logger log = LoggerFactory.getLogger(UserApi.class);

    @Inject
    private TodoRepository todoRepository;

    @Inject
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<UsersResponse>> users() throws URISyntaxException {
        log.debug("GET /users");
        List<User> result = userRepository.users();
        return ResponseEntity.ok().body(result.stream().map(this::convertToUsersResponse).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/userTodos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserTodosResponse>> userTodos(@Valid @RequestBody UserTodosRequest request) throws URISyntaxException {
        log.debug("GET /userTodos {}", request);
        List<Todo> result = todoRepository.userTodosPaged(request.getUserId(), request.getDrop(), request.getTake());
        return ResponseEntity.ok().body(result.stream().map(this::convertToUserTodosResponse).collect(Collectors.toList()));
    }

    private UsersResponse convertToUsersResponse(User model) {
        UsersResponse dto = new UsersResponse();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setRole(model.getRole());
        dto.setEmail(model.getEmail());
        dto.setPasswordHash(model.getPasswordHash());
        dto.setEmailVerificationCode(model.getEmailVerificationCode().orElse(null));
        dto.setEmailVerificationCodeTimestamp(model.getEmailVerificationCodeTimestamp().orElse(null));
        dto.setEmailVerified(model.getEmailVerified());
        return dto;
    }

    private UserTodosResponse convertToUserTodosResponse(Todo model) {
        UserTodosResponse dto = new UserTodosResponse();
        dto.setId(model.getId());
        dto.setUserId(model.getUser().getId());
        dto.setTask(model.getTask());
        dto.setDate(model.getDate());
        return dto;
    }
}
