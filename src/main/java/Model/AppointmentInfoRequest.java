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

    public Boolean getIsNewPatientAppointment() {
        return isNewPatientAppointment;
    }

    public void setIsNewPatientAppointment(Boolean newPatientAppointment) {
        isNewPatientAppointment = newPatientAppointment;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "AppointmentInfoRequest{" +
                "doctorId=" + doctorId +
                ", personId=" + personId +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", isNewPatientAppointment=" + isNewPatientAppointment +
                ", requestId=" + requestId +
                '}';
    }
}
