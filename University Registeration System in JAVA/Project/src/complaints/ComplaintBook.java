package complaints;

import java.util.ArrayList;
import java.util.List;

public class ComplaintBook { //this is to manage list of all complaints created by different students
    private List<Complaint> complaints;

    public ComplaintBook() {
        this.complaints = new ArrayList<>();
    }

    public void addComplaint(Complaint complaint) { //adding a new complaint
        complaints.add(complaint);
        System.out.println("Complaint filed successfully! ID: " + complaint.getComplaintID());
    }

    public List<Complaint> getAllComplaints() {
        return complaints;
    }

    public List<Complaint> getComplaints(String status) { //for extracting complaints of particularr status
        List<Complaint> result = new ArrayList<>();
        for (Complaint complaint : complaints) {
            boolean matchesStatus = ((status == null || complaint.getStatus().equals(status)));
            if (matchesStatus ) {
                result.add(complaint);
            }
        }
        return result;
    }
    public List<Complaint> getComplaintsbydate(String status , String date) { //for extracting complaints of particular date
        List<Complaint> result = new ArrayList<>();
        for (Complaint complaint : complaints) {
            boolean matchesStatus = ((status == null || complaint.getStatus().equals(status)) && complaint.getDate() == date);
            if (matchesStatus ) {
                result.add(complaint);
            }
        }
        return result;
    }


    public Complaint getComplaintByID(int complaintID) { //finding a complaint in list using id
        for (Complaint complaint : complaints) {
            if (complaint.getComplaintID() == complaintID) {
                return complaint;
            }
        }
        System.out.println("No complaint found with ID: " + complaintID);
        return null;
    }

    public boolean updateComplaintStatus(int complaintID, String newStatus) { //changing the complaint status
        Complaint complaint = getComplaintByID(complaintID);
        if (complaint != null) {
            complaint.setStatus(newStatus);
            System.out.println("Complaint ID " + complaintID + " status updated! Status: " + complaint.getStatus());
            return true;
        }
        return false;
    }
}
