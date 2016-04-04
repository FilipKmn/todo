package todo.backend.repository.tuple;

import todo.backend.model.*;


public class UserSessionUserTuple {

    private final UserSession userSession;
    private final User user;

    public UserSessionUserTuple(UserSession userSession, User user) {
        this.userSession = userSession;
        this.user = user;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public User getUser() {
        return user;
    }

}
