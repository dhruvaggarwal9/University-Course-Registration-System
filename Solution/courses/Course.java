    package courses;

    import professor.Professor;
    import student.Student;

    import java.util.ArrayList;
    import java.util.List;

    public class Course {
        private String courseCode;
        private String title;
        private Professor professor;
        private int credits;
        private String schedule;
        private int semester;
        private List<Course> prerequisites;
        private int enrollmentLimit;
        private int currentEnrollment;
        private String officeHours;
        private String syllabus;
        private List<Student> enrolledStudents;

        // Constructor
        public Course(String courseCode, String title, int credits,
                      int semester , String syllabus, String schedule) {
            this.courseCode = courseCode;
            this.title = title;
            this.professor = null;
            this.credits = credits;
            this.schedule = schedule;
            this.semester = semester;
            this.prerequisites = null  ;
            this.enrollmentLimit = Integer.MAX_VALUE; //No limit
            this.currentEnrollment = 0; // Initialize enrollment to 0
            this.syllabus = syllabus;
            this.enrolledStudents = new ArrayList<>();
            this.officeHours = "TBA by Professor"; //prof can set officehours after the course has been created and admin wil assign a proffesor to it
        }

        public List<Student> getEnrolledStudents() {
            return new ArrayList<>(enrolledStudents);
        }

        public void unenrollStudent(Student student) {
            enrolledStudents.remove(student);
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getTitle() {
            return title;
        }

        public String getProfessor() {
            return professor.getName();
        }

        public boolean hasProfessor() { //to check if proffesor has been assigned to this course
            if (professor!= null){
                return true;
            }
            return false;
        }

        public void setProfessor(Professor professor) {
            this.professor = professor;
        }

        public void setSyllabus(String syllabus) {
            this.syllabus = syllabus;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }

        public String getSchedule() {
            return schedule;
        }

        public void setSchedule(String schedule) {
            this.schedule = schedule;
        }

        public int getSemester() {
            return semester;
        }


        public List<Course> getPrerequisites() {
            return prerequisites;
        }

        public void setPrerequisites(List<Course> prerequisites) {
            this.prerequisites = prerequisites;
        }


        public void setEnrollmentLimit(int enrollmentLimit) {
            this.enrollmentLimit = enrollmentLimit;
        }

        public void setOfficeHours(String officeHours) {
            this.officeHours = officeHours;
        }

        public boolean enrollStudent() {
            if (currentEnrollment < enrollmentLimit) {
                currentEnrollment++;
                return true;
            }
            return false; //limit reached
        }

        public void enrollStudent(Student student) {
            if (!enrolledStudents.contains(student)) {
                enrolledStudents.add(student);
            }
        }
    }
