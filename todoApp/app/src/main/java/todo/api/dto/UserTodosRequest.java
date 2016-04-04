package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class UserTodosRequest {

    private final Long userId;
    private final Integer drop;
    private final Integer take;

    public UserTodosRequest(Long userId, Integer drop, Integer take) {
        this.userId = userId;
        this.drop = drop;
        this.take = take;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getDrop() {
        return drop;
    }

    public Integer getTake() {
        return take;
    }

}
