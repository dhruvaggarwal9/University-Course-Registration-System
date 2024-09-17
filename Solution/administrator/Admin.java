package administrator;

import courses.Course;
import courses.CourseCatalogue;
import student.Student;
import professor.Professor;
import complaints.Complaint;
import complaints.ComplaintManager;
import users.User;

import java.util.List;
public class Admin extends User {
    private static final String FIXED_EMAIL = "admin@college.com"; //fixing the email for admin login
    private static final String FIXED_PASSWORD = "admin@123"; //fixing pass also

    private CourseCatalogue courseCatalogue; //making instance of coursecatalogue
    private ComplaintManager complaintManager; //making instance of complaintmanager

    // Constructor
    public Admin(CourseCatalogue courseCatalogue, ComplaintManager complaintManager) { // Initializing admin with the credentials that cant be changed
        super(FIXED_EMAIL, FIXED_PASSWORD);
        this.courseCatalogue = courseCatalogue;
        this.complaintManager = complaintManager;
    }

    @Override
    public boolean login(String email, String password) { //implementing login method for admin that is present in user class
        if (FIXED_EMAIL.equals(email) && FIXED_PASSWORD.equals(password)) {
            System.out.println("Login successful. Welcome, " + email + "!");
            return true;
        } else {
            System.out.println("Login failed. Invalid email or password.");
            return false;
        }
    }

    @Override
    public void logout() { //implementing logout method for admin that is present in user class
        System.out.println("You have successfully logged out.");
    }

    @Override
    public boolean signup(String email, String password) { //Signup doesnt make any sense for admin as detail are fix
        System.out.println("Signup is not applicable for Admin.");
        return true;
    }

    public void viewAllCourses() { //view all courses in university course list
        System.out.println("Courses in the catalogue:");
        for (Course course : courseCatalogue.listCourses()) {
            System.out.println(course.getTitle() + " - " + course.getCourseCode());
        }
    }

    public void addCourse(Course course) { //adding a course to course catalogue
        courseCatalogue.addCourse(course);
        System.out.println("Course added: " + course.getTitle());
    }

    // Delete a course from the catalog
    public void deleteCourse(String courseCode) { //deleting a course from course catalogue
        // Find the course by its code
        Course courseToRemove = null;
        for (Course course : courseCatalogue.listCourses()) {
            if (course.getCourseCode().equals(courseCode)) { //finding the course to be deleted using course code by iterating through all courses
                courseToRemove = course;
                break;
            }
        }
        if (courseToRemove != null) {
            courseCatalogue.removeCourse(courseToRemove.getCourseCode()); //remove the course if found
            System.out.println("Course removed with code: " + courseCode);
        } else {
            System.out.println("Course with code " + courseCode + " not found."); //course not in catalogue
        }
    }


    public void assignProfessorToCourse(Professor professor, Course course) {  // Assign professor to a course
        if (professor != null && course != null) {
            professor.addCourse(course);
            System.out.println("Assigned professor " + professor.getEmail() + " to course " + course.getCourseCode());
        } else {
            System.out.println("Professor or course not found.");
        }
    }


    public void filterComplaints(String status) { // Filter complaints based on status or date //to be used in main system (not implemented as of tuesday night)
        List<Complaint> filteredComplaints = complaintManager.getComplaints(status); // Assume this method is available
        System.out.println("Filtered Complaints:");
        for (Complaint complaint : filteredComplaints) {
            System.out.println("ID: " + complaint.getComplaintID() + ", Description: "+ complaint.getDescription() + ", Status: " + complaint.getStatus() );
        }
    }

    public void assignGrade(Student student, Course course, String grade) { //Assigning grade to student
        if (student.getRegisteredCourses().contains(course)) { // Validate that the course is indeed registered by the student
            student.updateGrade(course, grade);
            System.out.println("Grade " + grade + " has been assigned to " + student.getName() +
                    " for the course: " + course.getTitle());
        } else {
            System.out.println("Student is not registered for the course: " + course.getTitle());
        }
    }

}
