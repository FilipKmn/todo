package todo.backend.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.*;


public class FindTodoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Min(0)
    private Integer drop;

    @NotNull
    @Min(0)
    private Integer take;

    public Integer getDrop() {
        return drop;
    }

    public void setDrop(Integer drop) {
        this.drop = drop;
    }

    public Integer getTake() {
        return take;
    }

    public void setTake(Integer take) {
        this.take = take;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FindTodoRequest other = (FindTodoRequest) obj;
        if (drop == null) {
            if (other.drop != null)
                return false;
        } else if (!drop.equals(other.drop))
            return false;
        if (take == null) {
            if (other.take != null)
                return false;
        } else if (!take.equals(other.take))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((drop == null) ? 0 : drop.hashCode());
        result = prime * result + ((take == null) ? 0 : take.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("FindTodoRequest [drop=%s, take=%s]", drop, take);
    }

}
