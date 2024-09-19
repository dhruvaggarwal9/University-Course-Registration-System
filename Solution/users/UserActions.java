package users;

public interface UserActions {
    boolean login(String email, String password);
    void logout();
    boolean signup(String email, String password);
}
 
