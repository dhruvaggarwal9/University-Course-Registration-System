package administrator;

import GradeChange.Request;
import GradeChange.RequestBook;
import helper.InvalidLoginException;
import courses.Course;
import courses.CourseBook;
import student.Student;
import professor.Professor;
import complaints.Complaint;
import complaints.ComplaintBook;
import users.User;

import java.util.List;

public class Admin extends User {
    private static final String FIXED_EMAIL = "admin@college.com"; //fixing the email for admin login
    private static final String FIXED_PASSWORD = "admin@123"; //fixing pass also

    private CourseBook courseBook; //making instance of coursecatalogue
    private ComplaintBook complaintBook; //making instance of complaintbook


    public Admin(CourseBook courseBook, ComplaintBook complaintBook) { // Initializing admin with the credentials that cant be changed
        super(FIXED_EMAIL, FIXED_PASSWORD);
        this.courseBook = courseBook;
        this.complaintBook = complaintBook;
    }

    @Override
    public boolean login(String email, String password) throws InvalidLoginException { //login method with fixed password and email defind above
        if (FIXED_EMAIL.equals(email) && FIXED_PASSWORD.equals(password)) {
            System.out.println("Login successful. Welcome, " + email + "!");
            return true;
        } else {
            throw new InvalidLoginException("Login failed. Invalid email or password."); // throwing the exception
        }
    }

    @Override
    public void logout() { //implementing logout method for admin that is present in user class
        System.out.println("You have successfully logged out.");
    } //logging out

    @Override
    public boolean signup(String email, String password) { //Signup doesnt make any sense for admin as detail are fix
        System.out.println("Signup is not applicable for Admin.");
        return true;
    }

    public void viewAllCourses() { //view all courses in university course list
        System.out.println("Courses in the catalogue:");
        for (Course course : courseBook.listCourses()) {
            System.out.println(course.getTitle() + " - " + course.getCourseCode());
        }
    }

    public void addCourse(Course course) { //adding a course to course catalogue
        courseBook.addCourse(course);
        System.out.println("Course added: " + course.getTitle());
    }

    // Delete a course from the catalog
    public void deleteCourse(String courseCode) { //deleting a course from course catalogue
        // Find the course by its code
        Course courseToRemove = null;
        for (Course course : courseBook.listCourses()) {
            if (course.getCourseCode().equals(courseCode)) { //finding the course to be deleted using course code by iterating through all courses
                courseToRemove = course;
                break;
            }
        }
        if (courseToRemove != null) {
            courseBook.removeCourse(courseToRemove.getCourseCode()); //remove the course if found
            System.out.println("Course removed with code: " + courseCode);
        } else {
            System.out.println("Course with code " + courseCode + " not found."); //course not in catalogue
        }
    }

    public void assignProfessorToCourse(Professor professor, Course course) {  // Assign professor to a course
        if (professor != null && course != null) {

            professor.addCourse(course);
            course.setProfessor(professor);
            System.out.println("Assigned professor " + professor.getEmail() + " to course " + course.getCourseCode());
        } else {
            System.out.println("Professor or course not found.");
        }
    }

    public void filterComplaints(String status) { //filter complaints based on status or date
        List<Complaint> filteredComplaints = complaintBook.getComplaints(status);
        System.out.println("Filtered Complaints:");
        for (Complaint complaint : filteredComplaints) {
            System.out.println("ID: " + complaint.getComplaintID() + ", Description: " + complaint.getDescription() + ", Status: " + complaint.getStatus());
        }
    }

    public void assignGrade(Student student, Course course, String grade) { //Assigning grade to student
        if (student.getRegisteredCourses().contains(course)) { // Validate that the course is indeed registered by the student
            student.updategrade(course, grade);
            System.out.println("Grade " + grade + " has been assigned to " + student.getName() + " for the course: " + course.getTitle());
        } else {
            System.out.println("Student is not registered for the course: " + course.getTitle());
        }
    }
    public void viewComplaint(ComplaintBook complaintBook, String status) {
        //list of complaints based on the given status
        List<Complaint> complaints = complaintBook.getComplaints(status);

        //check if there are no complaints available
        if (complaints.isEmpty()) {
            System.out.println("No complaints available.");
            return; // Exit the method if no complaints are found
        }

        //iterate through the list of complaints and display their details
        for (int i = 0; i < complaints.size(); i++) {
            Complaint complaint = complaints.get(i);
            System.out.println((i + 1) + ". " + complaint.getDescription() + " - " + (complaint.isResolved() ? "Resolved" : "Pending"));
        }
    }

    public void viewComplaintbydate(ComplaintBook complaintBook, String status, String date) {
        // the list of complaints based on the given status
        List<Complaint> complaints = complaintBook.getComplaints(status);

        //check if there are no complaints available
        if (complaints.isEmpty()) {
            System.out.println("No complaints available.");
            return; // Exit the method if no complaints are found
        }

        //iterate through the list of complaints to find those filed on the specified date
        for (int i = 0; i < complaints.size(); i++) {
            Complaint complaint = complaints.get(i);

            if (complaint.getDate().equals(date)) {
                System.out.println("Complaints filed on " + date);
                System.out.println((i + 1) + ". " + complaint.getDescription() + " - " + (complaint.isResolved() ? "Resolved" : "Pending"));
            }
        }
    }

    public void viewgradechangerequests(RequestBook requestBook) {
        //list of grade change requests that are currently pending
        List<Request> requests = requestBook.getRequests("Pending");

        //check if there are no requests available
        if (requests.isEmpty()) {
            System.out.println("No requests available.");
            return; // Exit the method if no requests are found
        }

        //iterate through the list of requests and display their details
        for (int i = 0; i < requests.size(); i++) {
            Request request = requests.get(i);
            System.out.println((i + 1) + ". " + request.getDescription() + " - " + (request.isResolved() ? "Resolved" : "Pending"));
        }
    }


}
