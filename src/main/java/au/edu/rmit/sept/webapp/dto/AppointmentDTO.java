package au.edu.rmit.sept.webapp.dto;

import au.edu.rmit.sept.webapp.models.CustomUser;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
        private Long appointmentID;
        private LocalDate appointmentDate;
        private LocalTime appointmentTime;
        private String petName;
        private CustomUser vet;
        private String generalNotes;
        private String status;
        private String clinicName;
        private Float fees;

    public AppointmentDTO() {
        
    }

    public AppointmentDTO(Long appointmentID, LocalDate appointmentDate, LocalTime appointmentTime, String petName, CustomUser vet, String generalNotes, String status, String clinicName, Float fees) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.petName = petName;
        this.vet = vet;
        this.generalNotes = generalNotes;
        this.status = status;
        this.clinicName = clinicName;
        this.fees = fees;
    }

    // Getters and Setters
    public Long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public CustomUser getVet() {
        return vet;
    }

    public void setVet(CustomUser vet) {
        this.vet = vet;
    }

    public String getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(String generalNotes) {
        this.generalNotes = generalNotes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Float getFees() {
        return fees;
    }

    public void setFees(Float fees) {
        this.fees = fees;
    }
}