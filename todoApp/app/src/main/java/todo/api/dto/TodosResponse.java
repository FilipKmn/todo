package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class TodosResponse {

    private final Long id;
    private final Long userId;
    private final String task;
    private final Date date;

    public TodosResponse(Long id, Long userId, String task, Date date) {
        this.id = id;
        this.userId = userId;
        this.task = task;
        this.date = date;
    }

    public Long getId() {
        return id;
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
