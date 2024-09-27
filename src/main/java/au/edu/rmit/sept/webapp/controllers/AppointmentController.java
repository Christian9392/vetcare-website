package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.dto.PetDTO;
import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.models.Pet;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import au.edu.rmit.sept.webapp.services.CustomUserDetailsService;
import au.edu.rmit.sept.webapp.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final CustomUserDetailsService userService;
    private final PetService petService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, CustomUserDetailsService userService, PetService petService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.petService = petService;
    }

    // Display appointment booking form
    @GetMapping("/new")
    public String bookAppointmentForm(Model model) {
        // Create a new AppointmentDTO instance
        AppointmentDTO appointmentDTO = new AppointmentDTO();

        // Get the current logged-in user and their pets
        CustomUser currentUser = userService.getCurrentUser();
        List<PetDTO> petDTOs = petService.findPetsByUserId(currentUser.getUserId());
        List<Pet> pets = petDTOs.stream()
                .map(dto -> new Pet(dto.getPetId(), dto.getName(), dto.getSpecies(), dto.getBreed(), dto.getAge()))
                .collect(Collectors.toList());

        // Add appointmentDTO and pets list to the model
        model.addAttribute("appointment", appointmentDTO);
        model.addAttribute("pets", pets);

        return "appointments/new";
    }

    // Handle form submission and save appointment
    @PostMapping("/book_appointment")
    public String bookAppointment(@ModelAttribute("appointment") AppointmentDTO appointmentDTO, RedirectAttributes redirectAttributes) {
        // Get the current user
        CustomUser currentUser = userService.getCurrentUser();

        // Get the pet by petId from the form
        Optional<Pet> optionalPet = petService.findPetBypetId(appointmentDTO.getPetId());

        // Check if the pet exists
        if (optionalPet.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Pet not found.");
            return "redirect:/appointments/new";
        }

        Pet pet = optionalPet.get();

        // Convert AppointmentDTO to Appointment entity
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setGeneralNotes(appointmentDTO.getGeneralNotes());
        appointment.setUser(currentUser);
        appointment.setPet(pet);

        // Save the appointment using the service
        appointmentService.createAppointment(appointment);

        // Add a success message
        redirectAttributes.addFlashAttribute("message", "Appointment booked successfully!");

        // Redirect to the appointments page
        return "redirect:/appointments";
    }

    @GetMapping
    public String viewAppointments(Model model) {
        // Get the current user
        CustomUser currentUser = userService.getCurrentUser();

        // Retrieve the user's appointments
        List<Appointment> appointments = appointmentService.getAppointmentsForUser(currentUser.getUserId());

        // Add appointments to the model
        model.addAttribute("appointments", appointments);

        return "appointments/index";
    }

}
