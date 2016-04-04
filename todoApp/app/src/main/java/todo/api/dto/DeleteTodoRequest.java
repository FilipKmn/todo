package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class DeleteTodoRequest {

    private final Long id;

    public DeleteTodoRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
