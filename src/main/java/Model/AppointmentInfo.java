package Model;

public class AppointmentInfo {
    private Integer doctorId;
    private Integer personId;
    private String appointmentTime;
    private Boolean isNewPatientAppointment;

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

    @Override
    public String toString() {
        return "AppointmentInfo{" +
                "doctorId=" + doctorId +
                ", personId=" + personId +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", isNewPatientAppointment=" + isNewPatientAppointment +
                '}';
    }
}
