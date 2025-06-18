package users;

import helper.InvalidLoginException;

public interface UserActions {
    boolean login(String email, String password) throws InvalidLoginException;
    void logout();
    boolean signup(String email, String password);
}
