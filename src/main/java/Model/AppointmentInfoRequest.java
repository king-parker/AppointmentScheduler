package Model;

public class AppointmentInfoRequest {
    private Integer doctorId;
    private Integer personId;
    private String appointmentTime;
    private Boolean isNewPatientAppointment;
    private Integer requestId;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Boolean getNewPatientAppointment() {
        return isNewPatientAppointment;
    }

    public void setNewPatientAppointment(Boolean newPatientAppointment) {
        isNewPatientAppointment = newPatientAppointment;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
}
