package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.models.Pet;
import au.edu.rmit.sept.webapp.services.CustomUserDetailsService;
import au.edu.rmit.sept.webapp.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Handles the view for displaying all registered pets for the currently logged-in user.
     */
    @GetMapping
    public String viewRegisteredPets(Model model) {
        // Get the currently authenticated user
        CustomUser currentUser = customUserDetailsService.getCurrentUser();

        // Fetch the pets associated with the current user
        List<Pet> pets = petService.findPetsByUserId(currentUser.getUserId());

        // Add the list of pets to the model
        if (!pets.isEmpty()) {
            model.addAttribute("pets", pets);
        } else {
            model.addAttribute("noPetsMessage", "You have no registered pets.");
        }

        // Return the view to display the registered pets
        return "pets/index";
    }
}
