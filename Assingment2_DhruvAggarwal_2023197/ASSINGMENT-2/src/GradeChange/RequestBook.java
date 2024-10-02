package GradeChange;

import java.util.ArrayList;
import java.util.List;

public class RequestBook { //this is to manage list of all Requests created by different students
    private static List<Request> Requests;

    public RequestBook() {
        this.Requests = new ArrayList<>();
    }

    public static void addRequest(Request Request) { //adding a new Request
        Requests.add(Request);
        System.out.println("Request filed successfully! ID: " + Request.getRequestID());
    }

    public List<Request> getAllRequests() {
        return Requests;
    }

    public List<Request> getRequests(String status) { //for extracting Requests of particularr status
        List<Request> result = new ArrayList<>();
        for (Request Request : Requests) {
            boolean matchesStatus = ((status == null || Request.getStatus().equals(status)));
            if (matchesStatus ) {
                result.add(Request);
            }
        }
        return result;
    }

}
