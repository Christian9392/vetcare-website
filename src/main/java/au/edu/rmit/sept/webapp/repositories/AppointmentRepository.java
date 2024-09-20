package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT new au.edu.rmit.sept.webapp.dto.AppointmentDTO(a.appointmentID, a.appointmentDate, a.appointmentTime, p.name, a.vet, a.generalNotes, a.status, c.name, a.fees) " +
            "FROM Appointment a " +
            "JOIN a.pet p " +
            "JOIN a.user u " +
            "JOIN a.clinic c")
    List<AppointmentDTO> findAllAppointments();

    @Query("SELECT new au.edu.rmit.sept.webapp.dto.AppointmentDTO(a.appointmentID, a.appointmentDate, a.appointmentTime, p.name, a.vet, a.generalNotes, a.status, c.name, a.fees) " +
            "FROM Appointment a " +
            "JOIN a.pet p " +
            "JOIN a.user u " +
            "JOIN a.clinic c " +
            "WHERE a.appointmentID = :id")
    AppointmentDTO findAppointmentById(@Param("id") Long id);
}
