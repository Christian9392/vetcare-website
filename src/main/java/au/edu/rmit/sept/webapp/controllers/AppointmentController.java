package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import au.edu.rmit.sept.webapp.services.ClinicService;
import au.edu.rmit.sept.webapp.services.CustomUserDetailsService;
import au.edu.rmit.sept.webapp.models.Pet;
import au.edu.rmit.sept.webapp.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import au.edu.rmit.sept.webapp.services.CustomUserDetailsService;
import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.models.CustomUser;

import java.util.List;
import java.util.Collection;
import java.time.*;

@Controller
public class AppointmentController {

    private AppointmentService appointmentService;
    private ClinicService clinicService;
    private CustomUserDetailsService userService;
    private PetService petService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, ClinicService clinicService, CustomUserDetailsService userService, PetService petService) {
        this.appointmentService = appointmentService;
        this.clinicService = clinicService;
        this.userService = userService;
        this.petService = petService;
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

    @GetMapping("/appointments/new")
    public String bookAppointment(Model model){
 
        AppointmentDTO appointment = new AppointmentDTO();                  
        List<Clinic> clinics = clinicService.getClinics(); 
        List<CustomUser> vets = userService.getVets();
        List<Pet> pets = petService.getPets();
        
        model.addAttribute("appointment", appointment);
        model.addAttribute("clinics", clinics);
        model.addAttribute("vets", vets);
        model.addAttribute("pets", pets);
        return "appointments/new";
    }

    @PostMapping("/appointments/book_appointment")
    public String addBooking(@ModelAttribute("appointment") AppointmentDTO appointment, RedirectAttributes redirectAttributes){

        //get objects from form data
        Clinic clinic = clinicService.findClinicByName(appointment.getClinicName());
        CustomUser vet = userService.findVetByName(appointment.getVetName());
        CustomUser user = userService.getCurrentUser();        
        Pet pet = petService.findPetByName(appointment.getPetName());

        //update appointment, clinic, user and pet
        appointmentService.createNewAppointment(appointment, clinic, user, vet, pet);
        redirectAttributes.addFlashAttribute("create_message", "Appointment booked successfully");
        return "redirect:/appointments";
    }
}
