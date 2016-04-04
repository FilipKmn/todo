package todo.api.dto;

import todo.api.enumeration.*;
import java.util.*;


public class FindTodoRequest {

    private final Integer drop;
    private final Integer take;

    public FindTodoRequest(Integer drop, Integer take) {
        this.drop = drop;
        this.take = take;
    }

    public Integer getDrop() {
        return drop;
    }

    public Integer getTake() {
        return take;
    }

}
