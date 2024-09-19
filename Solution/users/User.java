package users;

import helper.InputValidator;

public abstract class User implements UserActions{
    private String email;
    private String password;

    public User(String email, String password) {
        if (setEmail(email)) {
            if (InputValidator.isValidPassword(password)) {
                this.password = password;
            } else {
                this.password = "";
            }
        } else {
            System.out.println("Invalid email format.");
        }
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if (InputValidator.isValidEmail(email)) {
            this.email = email;
            return true;
        } else {
            System.out.println("Invalid email format. Please enter a valid email.");
            return false;
        }
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (InputValidator.isValidPassword(password)) {
            this.password = password;
            return true;
        } else {
            System.out.println("Invalid password format.");
            return false;
        }
    }
    @Override
    public abstract boolean login(String email, String password);

    @Override
    public abstract void logout();

    @Override
    public abstract boolean signup(String email, String password);
}
 
