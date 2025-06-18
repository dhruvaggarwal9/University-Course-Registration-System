package feedback;

import student.Student;
import java.util.ArrayList;

public class Feedback<T> {
    private static int idCounter = 1;
    private int feedbackID;
    private String studentEmail;  //rmail of student giving the feedback
    private String courseID;      //course ID for which feedback is given
    private T feedbackData;       //Generic data for feedback (could be numeric or textual)
    private String feedbackType;  //"Rating" for numeric, "Comment" for textual
    private String date;          //date of feedback submission

    public Feedback(String studentEmail, String courseID, T feedbackData, String feedbackType, String date) {
        this.feedbackID = idCounter++;
        this.studentEmail = studentEmail;
        this.courseID = courseID;
        this.feedbackData = feedbackData;
        this.feedbackType = feedbackType;
        this.date = date;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getCourseID() {
        return courseID;
    }

    public T getFeedbackData() {
        return feedbackData;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public String getDate() {
        return date;
    }

    public void displayFeedback() { //todisplay feedback when to be viewed by professor
        System.out.println("Feedback ID: " + feedbackID);
        System.out.println("Course ID: " + courseID);
        System.out.println("Feedback Type: " + feedbackType);
        System.out.println("Feedback: " + feedbackData);
        System.out.println("Date: " + date);
    }
}
