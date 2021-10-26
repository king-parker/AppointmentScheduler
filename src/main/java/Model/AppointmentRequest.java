package Model;

import java.util.List;

public class AppointmentRequest {
    private Integer requestId;
    private Integer personId;
    private List<String> preferredDays;
    private List<Integer> preferredDocs;
    private Boolean isNew;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public List<String> getPreferredDays() {
        return preferredDays;
    }

    public void setPreferredDays(List<String> preferredDays) {
        this.preferredDays = preferredDays;
    }

    public List<Integer> getPreferredDocs() {
        return preferredDocs;
    }

    public void setPreferredDocs(List<Integer> preferredDocs) {
        this.preferredDocs = preferredDocs;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean aNew) {
        isNew = aNew;
    }

    @Override
    public String toString() {
        return "AppointmentRequest{" +
                "requestId=" + requestId +
                ", personId=" + personId +
                ", preferredDays=" + preferredDays +
                ", preferredDocs=" + preferredDocs +
                ", isNew=" + isNew +
                '}';
    }
}
