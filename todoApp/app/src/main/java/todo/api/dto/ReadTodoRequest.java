package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class ReadTodoRequest {

    private final Long id;

    public ReadTodoRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
