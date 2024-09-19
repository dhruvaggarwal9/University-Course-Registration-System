import courses.Course;
import student.Student;
import professor.Professor;

public class ExampleUsage {
    public static void main(String[] args) {
        UniversitySystem system = new UniversitySystem();

        //creating 5 courses of sem1
        system.admin.addCourse(new Course("COM101", "Communication Skills", 4, 1, "Enhancing Communication Skills", "Wednesday 3:00-6:00 PM " + "C101"));
        system.admin.addCourse(new Course("CSE101", "Introduction to Programming", 4, 1, "Basic programming concepts in Python", "Monday, Wednesday 9:00-10:00 AM"));
        system.admin.addCourse(new Course("DES102", "Introduction to HCI", 4, 1, "UI Principles", "Monday, Wednesday 1:00-2:00 PM "  + "C101" ));
        system.admin.addCourse(new Course("ECE111", "Digital Circuits", 4, 1, "Designing circuits", "Tuesday, Thursday 9:00-10:00 AM "  + "C101"));
        system.admin.addCourse(new Course("MTH100", "Linear Algebra", 4, 1, "Linear Algebra basics", "Tuesday, Thursday 10:00-11:00 AM "  + "C101"));

        //creating 4 students for exmple for demo
        Student student1 = new Student("dhruv@iiitd.ac.in", "Dhruv@007", "Dhruv");
        student1.signup(student1.getEmail(), student1.getPassword());
        system.students.add(student1);

        Student student2 = new Student("ramneek@iiitd.ac.in", "Ramneek@007", "Ramneek");
        student2.signup(student2.getEmail(), student2.getPassword());
        system.students.add(student2);

        Student student3 = new Student("Anveshan@iiitd.ac.in", "Anveshan@007", "Anveshan");
        student3.signup(student3.getEmail(), student3.getPassword());
        system.students.add(student3);

        Student student4 = new Student("Pranav@iiitd.ac.in", "Pranav@007", "Pranav");
        student4.signup(student4.getEmail(), student4.getPassword());
        system.students.add(student4);

        //creating 5 profssors for 5 courses
        Professor professor1 = new Professor("Rajiv@iiitd.ac.in", "Rajiv@007", "Dr. Rajiv");
        system.professors.add(professor1);

        Professor professor2 = new Professor("Shubhajit@iiitd.ac.in", "Shubhajit@007", "Dr. Shubhajit");
        system.professors.add(professor2);

        Professor professor3 = new Professor("Payel@iiitd.ac.in", "Payel@007", "Dr. Payel");
        system.professors.add(professor3);

        Professor professor4 = new Professor("Pravesh@iiitd.ac.in", "Pravesh@007", "Dr. Pravesh");
        system.professors.add(professor4);

        Professor professor5 = new Professor("bnjain@iiitd.ac.in", "bnjain@007", "Dr. BN Jain");
        system.professors.add(professor5);


        //assigning the cuorses to professors
        system.admin.assignProfessorToCourse(professor1, system.courseBook.getCourse("DES102"));
        system.admin.assignProfessorToCourse(professor2, system.courseBook.getCourse("MTH100"));
        system.admin.assignProfessorToCourse(professor3, system.courseBook.getCourse("COM101"));
        system.admin.assignProfessorToCourse(professor4, system.courseBook.getCourse("ECE111"));
        system.admin.assignProfessorToCourse(professor5, system.courseBook.getCourse("CSE101"));


        //the 4 students register for all the 5 courses
        student1.registerCourse(system.courseBook.getCourse("CSE101"));
        student1.registerCourse(system.courseBook.getCourse("COM101"));
        student1.registerCourse(system.courseBook.getCourse("DES102"));
        student1.registerCourse(system.courseBook.getCourse("MTH100"));
        student1.registerCourse(system.courseBook.getCourse("ECE111"));

        student2.registerCourse(system.courseBook.getCourse("CSE101"));
        student2.registerCourse(system.courseBook.getCourse("COM101"));
        student2.registerCourse(system.courseBook.getCourse("DES102"));
        student2.registerCourse(system.courseBook.getCourse("MTH100"));
        student2.registerCourse(system.courseBook.getCourse("ECE111"));

        student3.registerCourse(system.courseBook.getCourse("CSE101"));
        student3.registerCourse(system.courseBook.getCourse("COM101"));
        student3.registerCourse(system.courseBook.getCourse("DES102"));
        student3.registerCourse(system.courseBook.getCourse("MTH100"));
        student3.registerCourse(system.courseBook.getCourse("ECE111"));

        student4.registerCourse(system.courseBook.getCourse("CSE101"));
        student4.registerCourse(system.courseBook.getCourse("COM101"));
        student4.registerCourse(system.courseBook.getCourse("DES102"));
        student4.registerCourse(system.courseBook.getCourse("MTH100"));
        student4.registerCourse(system.courseBook.getCourse("ECE111"));


        //professors update there office hours for courses
        professor1.updateOfficeHours("DES102", "10-11 AM WEDNESDAY");
        professor2.updateOfficeHours("MTH100", "10-11 AM FRIDAY");
        professor3.updateOfficeHours("COM101", "1-2 PM WEDNESDAY");
        professor4.updateOfficeHours("ECE111", "10-11 AM TUESDAY");
        professor5.updateOfficeHours("CSE101", "3-4 PM FRIDAY");

        //student1 view there schedule
        student1.viewSchedule();

        //prof5 viewieng his student list
        System.out.println("\nProfessor 5 viewing students in CSE101:");
        professor5.viewEnrolledStudents("CSE101");

        //admin assigning grades of 5 courses to all 4 students
        System.out.println("\nAdmin assigning grades for CSE101:");
        system.admin.assignGrade(student1, system.courseBook.getCourse("CSE101"), "A");
        system.admin.assignGrade(student2, system.courseBook.getCourse("CSE101"), "A");
        system.admin.assignGrade(student3, system.courseBook.getCourse("CSE101"), "A");
        system.admin.assignGrade(student4, system.courseBook.getCourse("CSE101"), "A");

        System.out.println("\nAdmin assigning grades for COM101:");
        system.admin.assignGrade(student1, system.courseBook.getCourse("COM101"), "B");
        system.admin.assignGrade(student2, system.courseBook.getCourse("COM101"), "B");
        system.admin.assignGrade(student3, system.courseBook.getCourse("COM101"), "B");
        system.admin.assignGrade(student4, system.courseBook.getCourse("COM101"), "B");

        System.out.println("\nAdmin assigning grades for DES102:");
        system.admin.assignGrade(student1, system.courseBook.getCourse("DES102"), "B");
        system.admin.assignGrade(student2, system.courseBook.getCourse("DES102"), "B");
        system.admin.assignGrade(student3, system.courseBook.getCourse("DES102"), "B");
        system.admin.assignGrade(student4, system.courseBook.getCourse("DES102"), "B");

        System.out.println("\nAdmin assigning grades for MTH100:");
        system.admin.assignGrade(student1, system.courseBook.getCourse("MTH100"), "A");
        system.admin.assignGrade(student2, system.courseBook.getCourse("MTH100"), "A");
        system.admin.assignGrade(student3, system.courseBook.getCourse("MTH100"), "A");
        system.admin.assignGrade(student4, system.courseBook.getCourse("MTH100"), "A");

        System.out.println("\nAdmin assigning grades for ECE111:");
        system.admin.assignGrade(student1, system.courseBook.getCourse("ECE111"), "A");
        system.admin.assignGrade(student2, system.courseBook.getCourse("ECE111"), "A");
        system.admin.assignGrade(student3, system.courseBook.getCourse("ECE111"), "A");
        system.admin.assignGrade(student4, system.courseBook.getCourse("ECE111"), "A");

        //Students viewing there result
        System.out.println("\nStudent 1 viewing their results:");
        student1.viewResult(1);

        System.out.println("\nStudent 2 viewing their results:");
        student2.viewResult(1);

        System.out.println("\nStudent 3 viewing their results:");
        student3.viewResult(1);

        //admin add 5 courses for sem 2
        system.admin.addCourse(new Course("CSE112", "Computer Organisation", 4, 2, "Basic insights on how computer works", "Wednesday 3:00-6:00 PM"));
        system.admin.addCourse(new Course("CSE102", "Data Structure and Algorithms", 4, 2, "All Data structures in C++", "Monday, Wednesday 9:00-10:00 AM"));
        system.admin.addCourse(new Course("ECE113", "Basic Electronics", 4, 2, "Electronic concepts", "Monday, Wednesday 1:00-2:00 PM"));
        system.admin.addCourse(new Course("ECO223", "Money & Banking", 4, 2, "Basic economic concepts", "Tuesday, Thursday 9:00-10:00 AM"));
        system.admin.addCourse(new Course("MTH201", "Probability & Statistics", 4, 2, "Applied Probability", "Tuesday, Thursday 10:00-11:00 AM"));

        //4 student register ffor 5 courses
        student1.registerCourse(system.courseBook.getCourse("CSE112"));
        student1.registerCourse(system.courseBook.getCourse("CSE102"));
        student1.registerCourse(system.courseBook.getCourse("ECE113"));
        student1.registerCourse(system.courseBook.getCourse("ECO223"));
        student1.registerCourse(system.courseBook.getCourse("MTH201"));

        student2.registerCourse(system.courseBook.getCourse("CSE112"));
        student2.registerCourse(system.courseBook.getCourse("CSE102"));
        student2.registerCourse(system.courseBook.getCourse("ECE113"));
        student2.registerCourse(system.courseBook.getCourse("ECO223"));
        student2.registerCourse(system.courseBook.getCourse("MTH201"));

        student3.registerCourse(system.courseBook.getCourse("CSE112"));
        student3.registerCourse(system.courseBook.getCourse("CSE102"));
        student3.registerCourse(system.courseBook.getCourse("ECE113"));
        student3.registerCourse(system.courseBook.getCourse("ECO223"));
        student3.registerCourse(system.courseBook.getCourse("MTH201"));

        student4.registerCourse(system.courseBook.getCourse("CSE112"));
        student4.registerCourse(system.courseBook.getCourse("CSE102"));
        student4.registerCourse(system.courseBook.getCourse("ECE113"));
        student4.registerCourse(system.courseBook.getCourse("ECO223"));
        student4.registerCourse(system.courseBook.getCourse("MTH201"));

        //new profesor for new course
        Professor professor6 = new Professor("ojaswa@iiitd.ac.in", "ojaswa@007", "Dr. Ojaswa");
        system.professors.add(professor6);

        Professor professor7 = new Professor("tammam@iiitd.ac.in", "tammam@007", "Dr. Tammam");
        system.professors.add(professor7);

        Professor professor8 = new Professor("Kirti@iiitd.ac.in", "Kirti@007", "Dr. Kirti");
        system.professors.add(professor8);

        Professor professor9 = new Professor("sujay@iiitd.ac.in", "sujay@007", "Dr. Sujay");
        system.professors.add(professor9);

        //assigning profesors to courses
        system.admin.assignProfessorToCourse(professor6, system.courseBook.getCourse("CSE102"));
        system.admin.assignProfessorToCourse(professor7, system.courseBook.getCourse("ECE113"));
        system.admin.assignProfessorToCourse(professor8, system.courseBook.getCourse("ECO223"));
        system.admin.assignProfessorToCourse(professor9, system.courseBook.getCourse("CSE112"));
        system.admin.assignProfessorToCourse(professor2, system.courseBook.getCourse("MTH201"));

        //professors update there office hours
        professor6.updateOfficeHours("CSE102", "10-11 AM WEDNESDAY");
        professor2.updateOfficeHours("MTH201", "10-11 AM FRIDAY");
        professor8.updateOfficeHours("ECO223", "10-11 AM TUESDAY");
        professor9.updateOfficeHours("CSE112", "3-4 PM FRIDAY");

        //student file complaint as office hour is not available for one course
        student2.filecomplaint("Office hour for ECE112 not available", system.complaintBook, "16/9/2024");

        //Admin views and resolves complaints
        system.viewComplaintbydate(system.complaintBook, "Pending" , "16/9/2024");
        System.out.println("\nAdmin views the complaint and asks the professor to add office hour");
        professor7.updateOfficeHours("COM101", "1-2 PM WEDNESDAY");
        System.out.println("\nProfessor adds the office hours for the given course.");
        system.resolveComplaint(system.complaintBook, system.complaintBook.getComplaintByID(1));
        System.out.println("\nAdmin marks complaint as resoled");

        //admin assign grqades to students
        System.out.println("\nAdmin assigning grades for CSE112:");
        system.admin.assignGrade(student1, system.courseBook.getCourse("CSE112"), "A");
        system.admin.assignGrade(student2, system.courseBook.getCourse("CSE112"), "A-");
        system.admin.assignGrade(student3, system.courseBook.getCourse("CSE112"), "A");
        system.admin.assignGrade(student4, system.courseBook.getCourse("CSE112"), "A");

        System.out.println("\nAdmin assigning grades for CSE102:");
        system.admin.assignGrade(student1, system.courseBook.getCourse("CSE102"), "B");
        system.admin.assignGrade(student2, system.courseBook.getCourse("CSE102"), "B");
        system.admin.assignGrade(student3, system.courseBook.getCourse("CSE102"), "B");
        system.admin.assignGrade(student4, system.courseBook.getCourse("CSE102"), "B");

        System.out.println("\nAdmin assigning grades for ECE113:");
        system.admin.assignGrade(student1, system.courseBook.getCourse("ECE113"), "B");
        system.admin.assignGrade(student2, system.courseBook.getCourse("ECE113"), "B");
        system.admin.assignGrade(student3, system.courseBook.getCourse("ECE113"), "B");
        system.admin.assignGrade(student4, system.courseBook.getCourse("ECE113"), "B");

        System.out.println("\nAdmin assigning grades for MTH201:");
        system.admin.assignGrade(student1, system.courseBook.getCourse("MTH201"), "A");
        system.admin.assignGrade(student2, system.courseBook.getCourse("MTH201"), "A");
        system.admin.assignGrade(student3, system.courseBook.getCourse("MTH201"), "A");
        system.admin.assignGrade(student4, system.courseBook.getCourse("MTH201"), "A");

        System.out.println("\nAdmin assigning grades for ECO223:");
        system.admin.assignGrade(student1, system.courseBook.getCourse("ECO223"), "A");
        system.admin.assignGrade(student2, system.courseBook.getCourse("ECO223"), "A");
        system.admin.assignGrade(student3, system.courseBook.getCourse("ECO223"), "D");
        system.admin.assignGrade(student4, system.courseBook.getCourse("ECO223"), "A");


        //students view there result
        System.out.println("\nStudent 1 viewing their results:");
        student1.viewResult(2); // assuming semester 1

        System.out.println("\nStudent 2 viewing their results:");
        student2.viewResult(2);

        System.out.println("\nStudent 3 viewing their results:");
        student3.viewResult(2);


        system.start();
    }
}
