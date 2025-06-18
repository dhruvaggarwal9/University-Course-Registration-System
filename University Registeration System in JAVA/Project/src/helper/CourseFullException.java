package helper;

public class CourseFullException extends Exception {
  public CourseFullException(String line) {
    super(line);
  }
}
