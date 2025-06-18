package feedback;

import student.Student;
import professor.Professor;
import java.util.ArrayList;
import java.util.List;

public class FeedbackBook {

    private static List<Feedback<?>> feedbackList; //List to store all feedback entries

    public FeedbackBook() {
        this.feedbackList = new ArrayList<>();
    }

    public static <T> void addFeedback(String studentEmail, String courseID, T feedbackData, String feedbackType, String date) {

        Feedback<T> feedback = new Feedback<>(studentEmail, courseID, feedbackData, feedbackType, date); //creating a new feedback
        feedbackList.add(feedback); //adding the feedback
        System.out.println("Feedback added successfully.");
    }


    public void viewFeedbackForCourse(String courseID) {
        boolean feedbackFound = false; //to track if feedback is found

        for (Feedback<?> feedback : feedbackList) {  // Iterate through all feedback entries
            if (feedback.getCourseID().equals(courseID)) {
                feedback.displayFeedback();//display the feedback details
                System.out.println("\n");
                feedbackFound = true;
            }
        }

        if (!feedbackFound) {
            System.out.println("No feedback available!");
        }
    }
}
