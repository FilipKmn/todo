package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class RestUpdateTodoRequest {

    private final Long userId;
    private final String task;
    private final Date date;

    public RestUpdateTodoRequest(Long userId, String task, Date date) {
        this.userId = userId;
        this.task = task;
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTask() {
        return task;
    }

    public Date getDate() {
        return date;
    }

}
