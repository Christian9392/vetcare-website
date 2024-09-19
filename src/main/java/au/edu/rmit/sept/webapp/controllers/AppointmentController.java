package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.Pet;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import au.edu.rmit.sept.webapp.services.AppointmentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import au.edu.rmit.sept.webapp.services.CustomUserDetailsService;
import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.models.CustomUser;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.time.*;

@Controller
public class AppointmentController {

    private AppointmentService appointmentService;
    private ClinicService clinicService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, ClinicService clinicService) {
        this.appointmentService = appointmentService;
        this.clinicService = clinicService;
    }

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // @Autowired
    // private PetService petService;

    @GetMapping("/appointments")
    public String getAppointments(Model model) {
        Collection<AppointmentDTO> appointments = appointmentService.getAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments/index";
    }

    @GetMapping("/appointments/view/{id}")
    public String viewAppointment(@PathVariable("id") Long id, Model model) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "appointments/view";
    }

    @GetMapping("/appointments/edit/{id}")
    public String editAppointment(@PathVariable("id") Long id, Model model) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "appointments/edit";
    }

    @PostMapping("/appointments/save")
    public String saveAppointment(@ModelAttribute("appointment") AppointmentDTO appointment, RedirectAttributes redirectAttributes) {
        appointmentService.saveAppointment(appointment);
        redirectAttributes.addFlashAttribute("save_message", "Appointment updated successfully.");
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        appointmentService.deleteAppointment(id);
        redirectAttributes.addFlashAttribute("delete_message", "Appointment deleted successfully.");
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/bookings")
    public String bookAppointment(@RequestParam LocalDate date, @RequestParam LocalTime time, Model model){
        AppointmentDTO appointment = new AppointmentDTO();

        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
        model.addAttribute("appointment", appointment);

        List<Clinic> clinics = clinicService.getClinics();
        model.addAttribute("clinics", clinics);
        return "appointments/bookings";
    }

    @PostMapping("/appointments/saveBooking")
    public String addBooking(@ModelAttribute("appointment") AppointmentDTO appointment, RedirectAttributes redirectAttributes){
        appointment.setStatus("Upcoming");

        Long Id = clinicService.findClinicIDByName(appointment.getClinicName());
        Clinic clinic = clinicService.findClinicByID(Id);

        appointmentService.saveAppointment(appointment, clinic);
        redirectAttributes.addFlashAttribute("create_message", "Appointment booked successfully");
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/new")
    public String newAppointment(Model model) {
        // Get the currently authenticated user, will be used later on for checking if pet is registered to user
        // CustomUser currentUser = customUserDetailsService.getCurrentUser();
        
        /* 
         * Skip checking for if pet is registered to user
         * Will implement after pet registration is implemented
        */
        model.addAttribute("appointmentDTO", new AppointmentDTO());

        return "appointments/new";
    }

    @PostMapping("/appointments/book")
    public String bookAppointment(AppointmentDTO appointmentDTO, Model model) {
        CustomUser currentUser = customUserDetailsService.getCurrentUser();

        // Validate date and time here (e.g., check if the slot is available)

        // Create a new appointment
        Appointment appointment = new Appointment();
        appointment.setUser(currentUser);
        //appointment.setPet(pet);
        appointment.setAppointmentDate((appointmentDTO.getAppointmentDate()));
        appointment.setAppointmentTime((appointmentDTO.getAppointmentTime()));
        appointment.setStatus(appointmentDTO.getStatus());

        // Save the appointment to the database
        appointmentService.saveAppointment(appointmentDTO);

        return "redirect:/appointments";  // Redirect to the appointments page after booking
    }


}
