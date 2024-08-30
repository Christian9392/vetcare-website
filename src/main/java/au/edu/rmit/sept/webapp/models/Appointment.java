package au.edu.rmit.sept.webapp.models;

public record Appointment(Long id, String date, String time, String petName, String veterinarianName, String notes, String status) {
}
