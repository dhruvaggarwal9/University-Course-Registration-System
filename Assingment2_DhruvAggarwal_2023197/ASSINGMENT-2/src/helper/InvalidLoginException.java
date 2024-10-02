package helper;

public class InvalidLoginException extends Exception {
    public InvalidLoginException(String line) {
        super(line);
    }
}
