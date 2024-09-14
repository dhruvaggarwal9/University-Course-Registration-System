- Classes needed - User, Student, Professor, Administrator, Course, Complaint
- Base classes - User, Course
- Student, Professor, Admin should inherit from User class
- Make a login/Sign-up mechanism
- Making a course catalogue - Add, remove, update the courses
- Make 3 interfaces (adding all features)



User Class (Abstract):
    Fields: email, password
    Methods: login(), logout(), signup()
    This class will serve as the base class for Student, Professor, and   Administrator.
    
Student Class (Inherits from User):
    Additional Fields: sem, registeredCourses[], Mygrades[]
    Methods: viewAvailableCourses(), registerCourses(), viewSchedule(), trackAcademicProgress(), dropCourse(), fileComplaint()
    
Professor Class (Inherits from User):
    Additional Fields: assignedCourses[]
    Methods: manageCourses(), viewEnrolledStudents()
    
Administrator Class (Inherits from User):
    Fields: courseCatalog[], studentRecords[], complaints[]
    Methods: manageCourseCatalog(), manageStudentRecords(), manageProfCourses(), manageComplaints()
    
Course Class:
    Fields: courseCode, title, professor, credits, prerequisites[], schedule
    Methods: getCourseDetails()
    
Complaint Class:
    Fields: description, status (Pending/Resolved), reply
    Methods: submitComplaint(), viewStatus()
