import GradeChange.Request;
import GradeChange.RequestBook;
import administrator.Admin;
import complaints.Complaint;
import courses.Course;
import courses.CourseBook;
import feedback.FeedbackBook;
import helper.DropDeadlinePassedException;
import helper.InvalidLoginException;
import student.Student;
import professor.Professor;
import complaints.ComplaintBook;
import student.TeachingAssistant;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class UniversitySystem {
    private LocalDate globalDropDeadline;
    CourseBook courseBook;
    ComplaintBook complaintBook;
    FeedbackBook feedbackBook;
    RequestBook requestBook;
    List<Student> students;
    List<Professor> professors;
    List<TeachingAssistant> taList;
    Admin admin;

    public UniversitySystem() {
        this.requestBook = new RequestBook();
        this.courseBook = new CourseBook();
        this.feedbackBook = new FeedbackBook();
        this.complaintBook = new ComplaintBook();
        this.students = new ArrayList<>();
        this.professors = new ArrayList<>();
        this.taList = new ArrayList<>();
        this.admin = new Admin(courseBook, complaintBook);
    }

    public static void main(String[] args) throws Exception {
        UniversitySystem system = new UniversitySystem();
        system.start();
    }


    public void start() throws Exception {
        System.out.println("Welcome to the University Course Registration System");
        while (true) {
            System.out.println("\n1. Enter the Application");
            System.out.println("2. Exit the Application");
            int choice = getUserChoice(1, 2);

            if (choice == 1) {
                handleUserEntry();
            } else {
                System.out.println("Thank you for using!");
                break;
            }
        }
    }

    private void handleUserEntry() throws Exception {
        System.out.println("\nDo you want to:");
        System.out.println("1. Sign up");
        System.out.println("2. Log in");
        int choice = getUserChoice(1, 2);
        if (choice == 1) {
            handleSignUp();
        } else {
            handleLogin();
        }
    }

    private void handleSignUp() throws Exception {
        System.out.println("\nSign up as:");
        System.out.println("1. Student");
        System.out.println("2. Professor");
        int userType = getUserChoice(1, 2);

        String email = manualinput("Enter email: ");
        String password = manualinput("Enter password: ");
        String name = manualinput("Enter name: ");

        if (userType == 1) {
            Student newStudent = new Student(email, password, name);
            if (newStudent.signup(email, password)) {
                students.add(newStudent);
                System.out.println("Student account created successfully.");
                studentMenu(newStudent);
            }
        } else {
            Professor newProfessor = new Professor(email, password, name);
            if (newProfessor.signup(email, password)) {
                professors.add(newProfessor);
                System.out.println("Professor account created successfully.");
                professorMenu(newProfessor);
            }
        }
    }

    private void handleLogin() throws Exception {
        System.out.println("\nLogin as:");
        System.out.println("1. Student");
        System.out.println("2. Professor");
        System.out.println("3. Administrator");
        int userType = getUserChoice(1, 3);

        String email = manualinput("Enter email: ");
        String password = manualinput("Enter password: ");

        switch (userType) {
            case 1:
                studentLogin(email, password);
                break;
            case 2:
                professorLogin(email, password);
                break;
            case 3:
                adminLogin(email, password);
                break;
        }
    }

    private void studentLogin(String email, String password) {
        Student student = findStudent(email);
        if (student != null) {
            try {
                if (student.login(email, password)) {
                    studentMenu(student);
                }
            } catch (InvalidLoginException e) {
                System.out.println(e.getMessage()); // Handle the specific login exception
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        } else {
            System.out.println("Student not found.");
        }
    }


    private void professorLogin(String email, String password) {
        Professor professor = findProfessor(email);
        if (professor != null) {
            try {
                if (professor.login(email, password)) {
                    professorMenu(professor);
                }
            } catch (InvalidLoginException e) {
                System.out.println(e.getMessage()); // Handle the specific login exception
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        } else {
            System.out.println("Professor not found.");
        }
    }


    private void adminLogin(String email, String password) {
        try {
            if (admin.login(email, password)) { // Assuming 'admin' is an instance of your Admin class
                adminMenu();
            }
        } catch (InvalidLoginException e) {
            System.out.println(e.getMessage()); // Handle the specific login exception
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    private Student findStudent(String email) {
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }

    private Professor findProfessor(String email) {
        for (Professor professor : professors) {
            if (professor.getEmail().equals(email)) {
                return professor;
            }
        }
        return null;
    }

    private void studentMenu(Student student) throws DropDeadlinePassedException {
        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. View available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. View schedule");
            System.out.println("5. View result");
            System.out.println("6. File a complaint");
            System.out.println("7. View personal info");
            System.out.println("8. View my complaints");
            System.out.println("9. Open my TA Dashboard");
            System.out.println("10. Give Feedbacks");
            System.out.println("11. Logout");
            int choice = getUserChoice(1, 11);

            switch (choice) {
                case 1:
                    student.viewAvailableCourses(courseBook, student.getSemester());
                    break;
                case 2:
                    registerForCourse(student);
                    break;
                case 3:
                    dropCourse(student);
                    break;
                case 4:
                    student.viewSchedule();
                    break;
                case 5:
                    int lastSemester = student.getSemester();
                    student.viewResult(lastSemester);
                    break;
                case 6:
                    String description = manualinput("Enter complaint description: ");
                    String date = manualinput("Enter date:(DD/MM/YYYY) ");
                    student.filecomplaint(description, complaintBook,date);
                    break;
                case 7:
                    System.out.println(student.getPersonalinfo(student));
                    break;
                case 8:
                    student.viewcomplaintStatus(complaintBook);
                    break;
                case 9:
                    TeachingAssistant ta = TeachingAssistant.findTAByEmail(student.getEmail(), taList);
                    TeachingAssistant.handleTeachingAssistantMenu(ta, requestBook);
                    break;
                case 10:
                    String courseID = manualinput("Enter Course ID for feedback: ");
                    String ratingInput = manualinput("Enter numeric rating (1-5) or type 'skip' for no rating: ");
                    String date1 = java.time.LocalDate.now().toString();

                    if (!ratingInput.equalsIgnoreCase("skip")) {
                        int rating = Integer.parseInt(ratingInput);
                        student.submitFeedback(feedbackBook, courseID, rating, "Rating", date1);
                    }

                    System.out.print("Enter textual feedback or type 'skip' for no feedback: ");
                    String commentInput = manualinput("Enter Textual feedback or type 'skip' for no rating: ");;

                    if (!commentInput.equalsIgnoreCase("skip")) {
                        student.submitFeedback(feedbackBook, courseID, commentInput, "Comment", date1);
                    }

                    break;
                case 11:
                    student.logout();
                    return;
            }
        }
    }

    private void registerForCourse(Student student) {
        int semester = manualinput2("Enter Semester: ");
        student.viewAvailableCourses(courseBook,semester);
        String courseCode = manualinput("Enter course code to register: ");
        Course course = courseBook.getCourse(courseCode);
        if (course != null) {
            if (student.registerCourse(course)) {
                System.out.println("Successfully registered for " + course.getTitle());
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private void dropCourse(Student student) {
        String courseCode = manualinput("Enter course code to drop: ");
        Course course = courseBook.getCourse(courseCode);
        if (course != null) {
            try {
                if (student.dropCourse(course, globalDropDeadline)) { // Pass the university system to access global deadline
                    System.out.println("Successfully dropped " + course.getTitle());
                }
            } catch (DropDeadlinePassedException e) {
                System.out.println(e.getMessage()); // Handle the exception and display the message
            }
        } else {
            System.out.println("Course not found.");
        }
    }


    private void professorMenu(Professor professor) {
        while (true) {
            System.out.println("\nProfessor Menu:");
            System.out.println("1. View managed courses");
            System.out.println("2. Update course details");
            System.out.println("3. View enrolled students");
            System.out.println("4. View Feedbacks");
            System.out.println("5. Logout");
            int choice = getUserChoice(1, 5);

            switch (choice) {
                case 1:
                    professor.viewCourses();
                    break;
                case 2:
                    updateCourseDetails(professor);
                    break;
                case 3:
                    viewEnrolledStudents(professor);
                    break;
                case 4:
                    String courseid = manualinput("Enter course ID to view feedback: ");
                    professor.viewFeedbackForCourse(feedbackBook, courseid);
                    break;
                case 5:
                    professor.logout();
                    return;
            }
        }
    }

    private void updateCourseDetails(Professor professor) {
        String courseId = manualinput("Enter course ID to update: ");
        Course course = professor.getCourse(courseId);
        if (course != null) {
            System.out.println("Select detail to update:");
            System.out.println("1. Syllabus");
            System.out.println("2. Class timings & venue");
            System.out.println("3. Credits");
            System.out.println("4. Prerequisites");
            System.out.println("5. Enrollment limit");
            System.out.println("6. Office hours");
            int detailChoice = getUserChoice(1, 6);

            switch (detailChoice) {
                case 1:
                    String syllabus = manualinput("Enter new syllabus: ");
                    professor.updateSyllabus(courseId, syllabus);
                    break;
                case 2:
                    String classTimings = manualinput("Enter new class timings: ");
                    String classVenue = manualinput("Enter new class Venue: ");
                    professor.updateClassTimings(courseId, classTimings +" in " + classVenue);
                    break;
                case 3:
                    int credits = manualinput2("Enter new credits: ");
                    professor.updateCredits(courseId, credits);
                    break;
                case 4:
                    List<Course> prerequisites = getPrerequisites();
                    professor.updatePrerequisites(courseId, prerequisites);
                    break;
                case 5:
                    int enrollmentLimit = manualinput2("Enter new enrollment limit: ");
                    professor.updateEnrollmentLimit(courseId, enrollmentLimit);
                    break;
                case 6:
                    String officeHours = manualinput("Enter new office hours: ");
                    professor.updateOfficeHours(courseId, officeHours);
                    break;
            }
        } else {
            System.out.println("Course not found or you don't have permission to update it.");
        }
    }

    private List<Course> getPrerequisites() {
        List<Course> prerequisites = new ArrayList<>();
        while (true) {
            String courseCode = manualinput("Enter prerequisite course code : ");
            if (courseCode.isEmpty()) break;
            Course course = courseBook.getCourse(courseCode);
            if (course != null) {
                prerequisites.add(course);
            } else {
                System.out.println("Course not found.");
            }
        }
        return prerequisites;
    }

    private void viewEnrolledStudents(Professor professor) {
        String courseId = manualinput("Enter course ID to view enrolled students: ");

        if (!professor.viewEnrolledStudents(courseId)) {
            System.out.println("Course not found or no students enrolled.");
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. Add a course");
            System.out.println("2. Remove a course");
            System.out.println("3. View course catalog");
            System.out.println("4. Manage student records");
            System.out.println("5. View complaints");
            System.out.println("6. Address complaints");
            System.out.println("7. Assign professor to a course");
            System.out.println("8. Assign Grades");
            System.out.println("9. Promote Student to TA");
            System.out.println("10. Grade change requests");
            System.out.println("11. Set DropDeadline");
            System.out.println("12. Logout");
            int choice = getUserChoice(1, 12);

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    removeCourse();
                    break;
                case 3:
                    viewCourseCatalog();
                    break;
                case 4:
                    manageStudentRecords();
                    break;

                case 5:
                    int status = manualinput2("Enter 1 to see Resolved Complaints. Enter 2 to see Pending Complaints.");
                    if (status ==1 ){

                        String choice0 = manualinput("Do you want to sort Complaints by date? Y/N ");
                        if (choice0 =="y" || choice0 =="Y") {
                            String date = manualinput("Date of complaint - (DD/MM/YYYY)" );
                            admin.viewComplaintbydate(complaintBook,"Resolved",date);
                            break;
                        }
                        else{
                            admin.viewComplaint(complaintBook,"Resolved");
                        }
                        break;
                    }
                    else{
                        String choice0 = manualinput("Do you want to sort Complaints by date? Y/N ");
                        if (choice0 =="y" || choice0 =="Y") {
                            String date = manualinput("Date of complaint - (DD/MM/YYYY)" );
                            admin.viewComplaintbydate(complaintBook,"Pending",date);
                            break;
                        }
                        else{
                            admin.viewComplaint(complaintBook,"Pending");
                            }
                        break;
                    }
                case 6:

                    String choice0 = manualinput("Do you want to sort Complaints by date? Y/N ");
                    if (choice0 =="y" || choice0 =="Y") {
                        String date = manualinput("Date of complaint - (DD/MM/YYYY)" );
                        admin.viewComplaintbydate(complaintBook,"Pending",date);

                        List<Complaint> complaints = complaintBook.getComplaintsbydate("Pending", date);
                        int choice1 = getUserChoice(1, complaints.size(), "Select complaint to resolve: ");
                        Complaint selectedComplaint = complaints.get(choice1 - 1);

                        resolveComplaint(complaintBook, selectedComplaint);
                        break;
                    }
                    else{

                    admin.viewComplaint(complaintBook,"Pending");

                        List<Complaint> complaints = complaintBook.getComplaints("Pending");
                        int choice1 = getUserChoice(1, complaints.size(), "Select complaint to resolve: ");
                        Complaint selectedComplaint = complaints.get(choice1 - 1);

                        resolveComplaint(complaintBook, selectedComplaint);}
                    break;
                case 7:
                    assignProfessorToCourse();
                    break;
                case 8:
                    String courseCode = manualinput("Enter the course code to assign grades:");

                    Course courseToGrade = CourseBook.getCourse(courseCode);

                    if (courseToGrade != null) {
                        List<Student> enrolledStudents = courseToGrade.getEnrolledStudents();

                        if (enrolledStudents.isEmpty()) {
                            System.out.println("No students are enrolled in this course.");
                        } else {
                            for (Student student : enrolledStudents) {
                                String grade = manualinput("Enter grade for " + student.getName() + ": ");
                                admin.assignGrade(student, courseToGrade, grade);
                                for (Request request : requestBook.getAllRequests()){
                                    if (Objects.equals(request.getStudentemail(), student.getEmail()) && Objects.equals(request.getCourse(), courseCode)){
                                        request.setStatus("DONE");
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Invalid course code.");
                    }
                    break;
                case 9:
                    promoteStudentToTA();
                    break;
                case 10:
                    admin.viewgradechangerequests(requestBook);
                    break;
                case 11:
                    String deadlineInput = manualinput("Enter the global course drop deadline (YYYY-MM-DD): ");
                    LocalDate deadline = LocalDate.parse(deadlineInput);
                    setGlobalDropDeadline(deadline);
                    break;
                case 12:
                    return;
            }
        }
    }


    private void promoteStudentToTA() {
        String email = manualinput("Enter student email to promote: ");
        Student student = findStudent(email);

        if (student != null) {
            String courseCode  = manualinput("Enter course code for TA to :");
            List<Course> assistingCourses = new ArrayList<>();
            Course course = CourseBook.getCourse(courseCode);
                if (course != null) {

                    assistingCourses.add(course);
                }
                else{
                    System.out.println("Course not found.");
                    return;
                }

            TeachingAssistant ta = new TeachingAssistant(student.getEmail(), student.getPassword(), student.getName());
            ta.setmycourses(assistingCourses);
            taList.add(ta);
            System.out.println(student.getName() + " has been promoted to Teaching Assistant.");
        } else {
            System.out.println("Student not found.");
        }
    }


    private void assignProfessorToCourse() {
        String courseId = manualinput("Enter course ID to assign a professor: ");
        Course course = courseBook.getCourse(courseId);

        if (course != null) {
            String professorEmail = manualinput("Enter professor email to assign: ");
            Professor professor = findProfessor(professorEmail);

            if (professor != null) {
                admin.assignProfessorToCourse(professor, course);
                System.out.println("Professor " + professor.getName() + " assigned to course " + course.getTitle() + " successfully.");
            } else {
                System.out.println("Professor not found.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }


    private void addCourse() {
        String id = manualinput("Enter course ID: ");
        String title = manualinput("Enter course title: ");
        int credits = manualinput2("Enter course credits: ");
        int semester = manualinput2("Enter course semester: ");
        String syllabus = manualinput("Enter course syllabus: ");
        String classTimings = manualinput("Enter class timings and day: ");
        String classVenue = manualinput("Enter class Venue: ");

        Course newCourse = new Course(id, title, credits, semester, syllabus, classTimings + " in " + classVenue);
        admin.addCourse(newCourse);
        System.out.println("Course added successfully.");
    }

    private void removeCourse() {
        String courseId = manualinput("Enter course ID to remove: ");
        admin.deleteCourse(courseId);
        System.out.println("Course removed successfully.");
    }

    private void viewCourseCatalog() {
        admin.viewAllCourses();
    }

    private void manageStudentRecords() {
        String email = manualinput("Enter student email: ");
        Student student = findStudent(email);
        if (student != null) {
            System.out.println("1. Change Password");
            System.out.println("2. Change email");
            System.out.println("3. Update Grades");
            int choice = getUserChoice(1, 3);
            switch (choice) {
                case 1:
                    String pass = manualinput("Enter New Password - ");
                    student.setPassword(pass);
                    break;
                case 2:
                    String mail = manualinput("Enter New E-Mail - ");
                    student.setEmail(mail);
                    break;
                case 3:

                    String courseCode = manualinput("Enter the course to grade - ");
                    String grade = manualinput("Enter the Grade - ");
                    Course courseToGrade = CourseBook.getCourse(courseCode);

                    if (courseToGrade != null) {
                                admin.assignGrade(student, courseToGrade, grade);


                    } else {
                        System.out.println("Invalid course code.");
                    }
                    break;
            }

        } else {
            System.out.println("Student not found.");
        }
    }

    void resolveComplaint(ComplaintBook complaintBook, Complaint selectedComplaint) {
        List<Complaint> complaints = complaintBook.getComplaints("Pending");

        if (complaints.isEmpty()) {
            System.out.println("No pending complaints to resolve.");
            return;
        }

        complaintBook.updateComplaintStatus(selectedComplaint.getComplaintID(), "SOLVED");
        System.out.println("Complaint marked as resolved.");
    }

    public void setGlobalDropDeadline(LocalDate deadline) {
        this.globalDropDeadline = deadline;
        System.out.println("Global drop deadline set to: " + globalDropDeadline);
    }
    public LocalDate getGlobalDropDeadline() {
        return globalDropDeadline;
    }



    private int getUserChoice(int min, int max, String prompt) {
        int choice = manualinput2(prompt);
        while (choice < min || choice > max) {

            choice = manualinput2("Invalid choice, please try again.");
        }
        return choice;
    }

    private int getUserChoice(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            try {
                choice = manualinput2("");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }


    private String manualinput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int manualinput2(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextInt();
    }
}
