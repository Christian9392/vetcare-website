package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.*;
import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AppointmentDTO> getAppointments() {
        return repository.findAllAppointments();
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        return repository.findAppointmentById(id);
    }

    @Override
    public void saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentID(appointmentDTO.getAppointmentID());
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setGeneralNotes(appointmentDTO.getGeneralNotes());
        appointment.setStatus(appointmentDTO.getStatus());
        repository.save(appointment);
    }

    @Override
    public void saveAppointment(AppointmentDTO appointmentDTO, Clinic clinic, CustomUser user) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentID(appointmentDTO.getAppointmentID());
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setGeneralNotes(appointmentDTO.getGeneralNotes());
        appointment.setStatus(appointmentDTO.getStatus());

        //temp, change fee based on what appointment type user picks
        appointment.setFees(50F);

        //update clinic and user
        appointment.setClinic(clinic);
        appointment.setUser(user);
        repository.save(appointment);
    }


    @Override
    public void deleteAppointment(Long id) {
        repository.deleteById(id);
    }
}
