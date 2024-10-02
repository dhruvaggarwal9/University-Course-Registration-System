package student;

import complaints.Complaint;
import complaints.ComplaintBook;
import users.User;
import courses.Course;
import courses.CourseBook;
import java.util.List;
import java.util.Map;
import GradeChange.Request;
import GradeChange.RequestBook;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class TeachingAssistant extends Student {
    private List<Course> mycourses;

    public TeachingAssistant(String email, String password, String name) {
        super(email, password, name);
    }

    public void setmycourses(List<Course> courses) {
        this.mycourses = courses;
    }

    public List<Course> getmycourses() {
        return mycourses;
    }

    public void viewStudentGrades(Course course, List<Student> students) {
        if (mycourses.contains(course)) {
            System.out.println("Grades for " + course.getTitle() + ":");
            for (Student student : students) {
                Map<Course, String> studentGrades = student.getCompletedCourses();
                if (studentGrades.containsKey(course)) {
                    System.out.println(student.getName() + ": " + studentGrades.get(course));
                }
            }
        } else {
            System.out.println("You are not authorized to view grades for this course.");
        }
    }

    public void filegradechangerequest (Course course, Student student, String newGrade, RequestBook requestBook) {
        String description = "";
        LocalDate currentDate = LocalDate.now(); // Get the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Define the format
        String formattedDate = currentDate.format(formatter); // Format the date

        if (mycourses.contains(course)) {
            description = ("Proposing grade change for " + student.getName() + " in " + course.getTitle() + " to " + newGrade);
            System.out.println("Request generated Successfully");

        } else {
            System.out.println("You are not authorized to propose grade changes for this course.");
        }

        Request request = new Request(this.getEmail(), description, formattedDate, student.getEmail(), course.getCourseCode());
        requestBook.addRequest(request);
    }

    @Override
    public String toString() {
        return "TeachingAssistant{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", semester=" + getSemester() +
                '}';
    }

    public static void handleTeachingAssistantMenu(TeachingAssistant ta, RequestBook requestBook) {
        while (true) {
            System.out.println("\nTeaching Assistant Menu:");
            System.out.println("1. View Assisting Courses");
            System.out.println("2. View Student Grades");
            System.out.println("3. Propose Grade Change");
            System.out.println("4. Return to Student Menu");
            int choice = getUserChoice(1, 4);

            switch (choice) {
                case 1:
                    viewAssistingCourses(ta);
                    break;
                case 2:
                    viewStudentGrades(ta);
                    break;
                case 3:
                    proposeGradeChange(ta, requestBook);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getUserChoice(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < min || choice > max) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }

    private static void viewAssistingCourses(TeachingAssistant ta) {
        if (ta ==null){
            System.out.println("YOU ARE NOT A TA");
        }
        else{
        List<Course> assistingCourses = ta.getmycourses();
        System.out.println("Courses you are assisting:");
        if (assistingCourses != null){
        for (Course course : assistingCourses) {
            System.out.println(course.getTitle() + " - " + course.getCourseCode());
        }}
        else{
            System.out.println("You are NOT assisting any course.");
        }}
    }

    public static TeachingAssistant findTAByEmail(String email, List<TeachingAssistant> taList) {
        for (TeachingAssistant ta : taList) {
            if (ta.getEmail().equals(email)) {
                return ta; // Return the matching TA
            }
        }
        return null; // Return null if no TA is found with the given email
    }

    private static void viewStudentGrades(TeachingAssistant ta) {
        viewAssistingCourses(ta);
        String courseCode = ta.manualinput("Enter course code to view grades : ");
        Course course = CourseBook.getCourse(courseCode);
        if (course != null && ta.getmycourses().contains(course)) {
            List<Student> enrolledStudents = course.getEnrolledStudents();
            ta.viewStudentGrades(course, enrolledStudents);
        } else {
            System.out.println("Invalid course code or you're not assisting this course.");
        }
    }

    private static void proposeGradeChange(TeachingAssistant ta, RequestBook requestBook) {
        viewAssistingCourses(ta);
        String courseCode = ta.manualinput("Enter course code: ");
        Student student = null;
        Course course = CourseBook.getCourse(courseCode);
        if (course != null && ta.getmycourses().contains(course)) {
            String studentEmail = ta.manualinput("Enter student email: ");
            List<Student> allstudents = course.getEnrolledStudents();
            for (Student element : allstudents) {
                if (Objects.equals(element.getEmail(), studentEmail)) {
                    student = element;
                    break;
                }
            }
            if (student != null) {
                String newGrade = ta.manualinput("Enter proposed grade: ");
                ta.filegradechangerequest(course, student, newGrade, requestBook);
            } else {
                System.out.println("Student not found in this course.");
            }
        } else {
            System.out.println("Invalid course code or you're not assisting this course.");
        }
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
