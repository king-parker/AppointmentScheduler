package Model;

import java.util.List;

public class AppointmentRequest {
    private Integer requestId;
    private Integer personId;
    private List<String> preferredDays;
    private List<Integer> preferredDocs;
    private Boolean isNew;
}
