package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import au.edu.rmit.sept.webapp.services.ClinicService;
import au.edu.rmit.sept.webapp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Collection;
import java.time.*;

@Controller
public class AppointmentController {

    private AppointmentService appointmentService;
    private ClinicService clinicService;
    private CustomUserDetailsService userService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, ClinicService clinicService, CustomUserDetailsService userService) {
        this.appointmentService = appointmentService;
        this.clinicService = clinicService;
        this.userService = userService;
    }

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

        //get clinic entity using name picked by user
        Long Id = clinicService.findClinicIDByName(appointment.getClinicName());
        Clinic clinic = clinicService.findClinicByID(Id);

        //get current user
        CustomUser user = userService.getCurrentUser();

        //update appointment, clinic and user
        appointmentService.saveAppointment(appointment, clinic, user);

        //confirmation message
        redirectAttributes.addFlashAttribute("create_message", "Appointment booked successfully");
        return "redirect:/appointments";
    }
}
