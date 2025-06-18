package professor;

import helper.InvalidLoginException;
import users.User;
import courses.Course;
import student.Student;
import helper.InputValidator;
import feedback.FeedbackBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Professor extends User {
    private String name;
    private Map<String, Course> courses; // string is for course code to mantain as record
    private Map<Student, List<Course>> enrolledStudents; //enrolled students in the courses of prof

    public Professor(String email, String password, String name) {
        super(email, password);
        this.courses = new HashMap<>();
        this.enrolledStudents = new HashMap<>();
        this.name = name;
    }

    public Course getCourse(String courseId) {
        for (Course course : courses.values()) {
            if (course.getCourseCode().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public void viewFeedbackForCourse(FeedbackBook feedbackBook, String courseID) {
        System.out.println("Viewing feedback for course: " + courseID);
        feedbackBook.viewFeedbackForCourse(courseID);
    }

    public void viewCourses() { //view courses managed by this prof
        System.out.println("Courses managed by Professor " + this.getEmail() + ":");
        for (Course course : courses.values()) {
            System.out.println(course.getTitle() + " (" + course.getCourseCode() + ")");
        }
    }

    public void updateSyllabus(String courseCode, String syllabus) { //updating the syllabus of the course
        Course course = courses.get(courseCode);
        if (course != null) {
            course.setSyllabus(syllabus);
            System.out.println("Syllabus updated for course: " + course.getTitle());
        } else {
            System.out.println("Course with code " + courseCode + " not found.");
        }
    }

    public void updateClassTimings(String courseCode, String classTimings) { //to update class timings of a course
        Course course = courses.get(courseCode);
        if (course != null) {
            course.setSchedule(classTimings);
            System.out.println("Class timings updated for course: " + course.getTitle());
        } else {
            System.out.println("Course with code " + courseCode + " not found.");
        }
    }

    public void updateCredits(String courseCode, int credits) { //to update credits of a course
        Course course = courses.get(courseCode);
        if (course != null) {
            course.setCredits(credits);
            System.out.println("Credits updated for course: " + course.getTitle());
        } else {
            System.out.println("Course with code " + courseCode + " not found.");
        }
    }

    public void updatePrerequisites(String courseCode, List<Course> prerequisites) { //update prerequisites of a course
        Course course = courses.get(courseCode);
        if (course != null) {
            course.setPrerequisites(prerequisites);
            System.out.println("Prerequisites updated for course: " + course.getTitle());
        } else {
            System.out.println("Course with code " + courseCode + " not found.");
        }
    }

    public String getName() {
        return name;
    }

    public void updateEnrollmentLimit(String courseCode, int enrollmentLimit) { //update enrolment limit
        Course course = courses.get(courseCode);
        if (course != null) {
            course.setEnrollmentLimit(enrollmentLimit);
            System.out.println("Enrollment limit updated for course: " + course.getTitle());
        } else {
            System.out.println("Course with code " + courseCode + " not found.");
        }
    }

    public void updateOfficeHours(String courseCode, String officeHours) { //updating office hours
        Course course = courses.get(courseCode);
        if (course != null) {
            course.setOfficeHours(officeHours);
            System.out.println("Office hours updated for course: " + course.getTitle());
        } else {
            System.out.println("Course with code " + courseCode + " not found.");
        }
    }

    public boolean viewEnrolledStudents(String courseCode) { //to get info about his/her students
        Course course = courses.get(courseCode);
        if (course != null) {
            System.out.println("Enrolled students for course " + course.getTitle() + ":");
            for (Student student : course.getEnrolledStudents()) {
                    System.out.println("Student: " + student.getName() + " (" + student.getEmail() + ")" );

            }
            return true;
        } else {
            System.out.println("Course with code " + courseCode + " not found.");

        }
        return false;
    }

    public void addCourse(Course course) {

        courses.put(course.getCourseCode(), course);
    }

    public void removeCourse(String courseCode) {
        courses.remove(courseCode);
    }

    @Override
    public boolean login(String email, String password) throws InvalidLoginException {
        if (this.getEmail().equals(email) && this.getPassword().equals(password)) {
            System.out.println("Login successful. " + email + "!");
            return true;
        } else {
            throw new InvalidLoginException("Login failed. Invalid email or password.");
        }
    }

    @Override
    public void logout() {
        System.out.println("You have successfully logged out.");
    }

    @Override
    public boolean signup(String email, String password) {
        if (InputValidator.isValidEmail(email) && InputValidator.isValidPassword(password)) {
            this.setEmail(email);
            this.setPassword(password);
            System.out.println("Signup successful. Welcome, " + email + "!");
            return true;
        } else {
            System.out.println("Signup failed. Invalid email or password format.");
            return false;
        }
    }
}
