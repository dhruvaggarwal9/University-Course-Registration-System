package courses;

import java.util.ArrayList;
import java.util.List;

public class CourseBook {
    private static List<Course> courses;

    public CourseBook() { //initialising the course book
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean removeCourse(String id) { //removing a course form course book
       for (Course course : courses){
           if (course.getCourseCode() == id){
               courses.remove(course);
               return true;
           }
       }
       return false;
  }

    public static Course getCourse(String courseId) { //for finding the course by id
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public List<Course> listCourses() { //listof all courses
        return new ArrayList<>(courses);
    }

    public List<Course> getAvailableCourses(int semester) { //Courses of a particular sem
        List<Course> availableCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getSemester() == semester) {
                availableCourses.add(course);
            }
        }
        return availableCourses;
    }
}
