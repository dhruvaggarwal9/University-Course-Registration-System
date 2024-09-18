package student;

import users.User;
import courses.Course;
import courses.CourseBook;
import complaints.Complaint;
import utilities.InputValidator;
import complaints.ComplaintBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Student extends User {
    private String name;
    private int semester;
    private List<Course> registeredCourses;   //courses registered in the current semester
    private Map<Course, String> completedCourses;     //courses the student has completed prevously (String for grade)
    private Map<Course, String> grades;       //grades for completed courses
    private int creditsTaken;

    public Student(String email, String password, String name) {
        super(email, password);
        this.registeredCourses = new ArrayList<>();
        this.completedCourses = new HashMap<>();
        this.name = name;
        this.semester = 1; //given in assingment that all student start from sem 1
        this.grades = new HashMap<>();
        this.creditsTaken = 0;
    }

    public void viewAvailableCourses(CourseBook catalog, int semester) {  //available courses for the current semester
        List<Course> availableCourses = catalog.getAvailableCourses(semester);
        System.out.println("Available Courses for Semester " + semester + ":");
        for (Course course : availableCourses) {
            System.out.println(course.getTitle() + " - " + course.getCourseCode() +
                    " (Credits: " + course.getCredits() + ")");
            if (course.hasProfessor() ){
                System.out.println("Professor - " + course.getProfessor() + " Timings - " + course.getSchedule());
            }
        }
    }

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("already registered for this course");
            return false;
        }

        if (!(course.getPrerequisites() == null)){
        for (Course prereq : course.getPrerequisites()) {

            if (!completedCourses.containsKey(prereq)) {
                System.out.println("You do not meet the prerequisites.");
                return false;
            }
        }}

        if (!course.enrollStudent()) {
            System.out.println("The course is full."); //limit reached
            return false;
        }

        if (creditsTaken + course.getCredits() > 20) { //if overload is happening, overload not allowed as per assingment
            System.out.println("You cannot register for this course as it exceeds the credit limit.");
            return false;
        }


        registeredCourses.add(course);
        creditsTaken += course.getCredits();
        course.enrollStudent(this);
        System.out.println("Successfully registered for the course: " + course.getTitle());
        return true;
    }

    public void updategrade(Course course, String newGrade) {
        if (registeredCourses.contains(course)) {
            grades.put(course, newGrade); //update grade
            completedCourses.put(course, newGrade); //move to completed courses
            creditsTaken-= course.getCredits();
            registeredCourses.remove(course); //remove from registered courses
            System.out.println("Grade updated for course: " + course.getTitle());
        } else {
            System.out.println("Course is not registered.");
        }
        if (registeredCourses.isEmpty()){
            semester++;
        }
    }

    public String getPersonalinfo(Student stu) {
        return ("Name - " + stu.name);
    }

    public boolean dropCourse(Course course) {
        if (!(registeredCourses.contains(course))) {
            System.out.println("Not registered for the course.");
            return false;
        }

        registeredCourses.remove(course);
        creditsTaken -= course.getCredits();
        course.unenrollStudent(this);
        System.out.println("Dropped the course: " + course.getTitle());
        return true;
    }

    public void viewSchedule() {
        System.out.println("Your Schedule for the Current Semester:");
        for (Course course : registeredCourses) {
            System.out.println(course.getTitle() + " (" + course.getCourseCode() + ")");
            System.out.println("Timings & Venue: " + course.getSchedule());
            System.out.println("Professor: " + course.getProfessor());
        }
    }

    public double calculateSGPA(int lastSemester) {
        double totalGradePoints = 0.0;
        int totalCredits = 0;

        System.out.println("SGPA for Semester " + lastSemester + ":");
        for (Course course : completedCourses.keySet()) {
            String grade = completedCourses.get(course);

            if (course.getSemester() == lastSemester) { //finding the course of last semester
                double gradePoint = convertLettertonumgrade(grade); //letter grade to number
                
                totalGradePoints += gradePoint * course.getCredits();
                totalCredits += course.getCredits();
                System.out.println(course.getTitle() + " (" + course.getCourseCode() + ") - Grade: " + grade);
            }
        }

        if (totalCredits > 0) {
            double sgpa = totalGradePoints / totalCredits;
            System.out.println("SGPA: " + sgpa);
            return sgpa;
        }
        else {
            System.out.println("No courses completed in Semester " + lastSemester);
            return 0.0;
        }
    }


    public double calculateCGPA() {
        double totalPoints = 0.0;
        int totalCredits = 0;


        for (Course course : completedCourses.keySet()) {
            String grade = completedCourses.get(course);

            double gradePoint = convertLettertonumgrade(grade); //letter grade to num
            totalPoints += gradePoint * course.getCredits();
            totalCredits += course.getCredits();
        }


        if (totalCredits > 0) {
            double cgpa = totalPoints / totalCredits;
            System.out.println("CGPA: " + cgpa);
            return cgpa;
        } else {
            System.out.println("No completed courses.");
            return 0.0;
        }
    }


    public void viewResult(int lastSemester) {
        double sgpa = calculateSGPA(lastSemester);
        double cgpa = calculateCGPA();


        System.out.println("SGPA for Semester " + lastSemester + ": " + sgpa);
        System.out.println("CGPA : " + cgpa);
    }


    private double convertLettertonumgrade(String grade) { //for converting letter grade to number
        switch (grade) {
            case "A":
                return 10.0;
            case "A-":
                return 9.0;
            case "B":
                return 8.0;
            case "B-":
                return 7.0;
            case "C":
                return 6.0;
            case "D":
                return 5.0;
            case "E":
                return 4.0;
            case "F":
                return 0.0;
            default:
                return 0.0;  
        }
    }


    @Override
    public boolean login(String email, String password) {
        if (this.getEmail().equals(email) && this.getPassword().equals(password)) {
            System.out.println("Login successful. " + email + "!");
            return true;
        } else {
            System.out.println("Login failed. Invalid email or password.");
            return false;
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
            System.out.println("Signup successful. " + email + "!");
            return true;
        } else {
            System.out.println("Signup failed. Invalid email or password format.");
            return false;
        }
    }

    public int getSemester() {
        return semester;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }
    
    public void viewcomplaintStatus(ComplaintBook complaintBook) {
        List<Complaint> allComplaints = complaintBook.getAllComplaints();

        boolean found = false;
        for (Complaint complaint : allComplaints) {
            if (complaint.getStudentEmail().equals(this.getEmail())) {  //Filter the complaints that belong to this student
                found = true;
                System.out.println("Complaint ID: " + complaint.getComplaintID() + " | Status: " + complaint.getStatus() + " | Text: " + complaint.getDescription());
            }
        }

        if (!found) {
            System.out.println("You have not filed any complaints.");
        }}

    public void filecomplaint(String description, ComplaintBook complaintBook, String date) {
        if (description == null || description.isEmpty()) {
            System.out.println("Complaint description cannot be empty.");
            return;
        }

        Complaint complaint = new Complaint(this.getEmail(), description, date);
        complaintBook.addComplaint(complaint);  }


}
