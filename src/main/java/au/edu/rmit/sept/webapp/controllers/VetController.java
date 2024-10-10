package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.dto.PetDTO;
import au.edu.rmit.sept.webapp.models.Pet;
import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.services.PetService;
import au.edu.rmit.sept.webapp.services.UserService;
import au.edu.rmit.sept.webapp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vet")
public class VetController {

    private final PetService petService;
    private final UserService userService;

    @Autowired
    public VetController(PetService petService, UserService userService, CustomUserDetailsService customUserDetailsService) {
        this.petService = petService;
        this.userService = userService;
    }

    // Display list of all users (owners) for veterinarian to choose from
    @GetMapping("/owners")
    public String viewAllUsers(Model model) {
        List<CustomUser> users = userService.getAllUsers(); // Assuming you have a method in UserService to fetch all users
        model.addAttribute("users", users);
        return "vet/selectUser"; // Thymeleaf view for selecting a user
    }

    // Once a user is selected, show all pets for that user
    @GetMapping("/owners/{userId}/pets")
    public String viewPetsByUserId(@PathVariable Long userId, Model model) {
        List<PetDTO> pets = petService.findPetsByUserId(userId);

        if (!pets.isEmpty()) {
            model.addAttribute("pets", pets);
        } else {
            model.addAttribute("noPetsMessage", "This user has no registered pets.");
        }

        return "vet/petList"; // Thymeleaf view to display list of pets for the selected user
    }
}
