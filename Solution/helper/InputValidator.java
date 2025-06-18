package helper;

public class InputValidator {

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        if (email.indexOf('@') < 1) {
            return false;  //@ can't be the first character
        }

        int dotIndex = email.indexOf('.', email.indexOf('@')); //check for .  after the @ symbol
         if (dotIndex < email.indexOf('@') + 2) { //least one character in between
            return false;
        }

        return dotIndex +2 < email.length(); //domain after . have 2 letter
    }


    public static boolean isValidPassword(String password) {
        //Pass should be 8 letter, have special character and a number
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasSpecialChar = false;
        boolean hasDigit = false;
        char[] passwordArr = password.toCharArray();
        for (char c : passwordArr) {
            if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }

        }
        return hasSpecialChar && hasDigit;
    }
}
