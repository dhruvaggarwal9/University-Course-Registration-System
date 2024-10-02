package complaints;

import student.Student;

public class Complaint {
    private static int idCounter = 1;
    private int complaintID;
    private String studentEmail; //email of student that raised the complaint
    private String description;
    private String status;  //Status can be "Pending", "Resolved"
    private String date; //date of complaint (needed to sort the complaints based ond date as aksed)

    public Complaint(String studentEmail, String description, String date) {
        this.complaintID = idCounter++;
        this.studentEmail = studentEmail;
        this.description = description;
        this.status = "Pending"; //pending as its a new complaint
        this.date = date;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public String getStudentEmail() {
        return studentEmail;
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

    public boolean isResolved() { //check if the status of the complaint is pending or not
        if (status == "Pending") {
            return false;
        }
        return true;
    }

    public void setStatus(String status) { //for changing statuus of strings
        this.status = status;
    }
}
