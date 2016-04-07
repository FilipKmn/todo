package todo.backend.rest;

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
import todo.backend.rest.dto.*;


@RestController
@RequestMapping("/api/")
public class AuthenticationApi {

    private final Logger log = LoggerFactory.getLogger(AuthenticationApi.class);

}
