package helper;

public class DropDeadlinePassedException extends Exception {
    public DropDeadlinePassedException(String line) {
        super(line);
    }
}
