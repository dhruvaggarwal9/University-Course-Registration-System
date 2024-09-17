package complaints;

import student.Student;

public class Complaint {
    private static int idCounter = 1;
    private int complaintID;
    private String studentEmail; //email of student that raised the complaint
    private String description;
    private String status;  //Status can be "Pending", "Resolved"

    public Complaint(String studentEmail, String description) {
        this.complaintID = idCounter++;
        this.studentEmail = studentEmail;
        this.description = description;
        this.status = "Pending";  // New complaints start as pending
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public boolean isResolved() { //check if the status of the complaint is pending or not
        if (status == "Pending"){
            return false;
        }
        return true;
    }

    public void setStatus(String status) { //for changing statuus of strings
        this.status = status;
    }
}
