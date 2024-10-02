package GradeChange;

import student.Student;

public class Request {
    private static int idCounter = 1;
    private int RequestID;
    private String TAemail; //email of TA that raised the Request
    private String description;
    private String status;  //Status can be "Pending", "Resolved"
    private String date;
    private String studentemail;
    private String Course;//date of Request (needed to sort the Requests based ond date as aksed)

    public Request(String TAemail, String description, String date, String studentemail, String Course) {
        this.RequestID = idCounter++;
        this.TAemail = TAemail;
        this.description = description;
        this.status = "Pending"; //pending as its a new Request
        this.date = date;
        this.Course = Course;
        this.studentemail = studentemail;
    }

    public int getRequestID() {
        return RequestID;
    }
    public String getStudentemail() {
        return studentemail;
    }

    public String getCourse() {
        return Course;
    }

    public String getTAemail() {
        return TAemail;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public boolean isResolved() { //check if the status of the Request is pending or not
        if (status == "Pending") {
            return false;
        }
        return true;
    }

    public void setStatus(String status) { //for changing statuus of strings
        this.status = status;
    }
}
