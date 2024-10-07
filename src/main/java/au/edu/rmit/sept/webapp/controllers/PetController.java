package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.dto.PetDTO;
import au.edu.rmit.sept.webapp.models.*;
import au.edu.rmit.sept.webapp.services.CustomUserDetailsService;
import au.edu.rmit.sept.webapp.services.PetMedicalHistoryService;
import au.edu.rmit.sept.webapp.services.PetService;
import au.edu.rmit.sept.webapp.services.PrescriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PetMedicalHistoryService petMedicalHistoryService;

    @Autowired
    private PrescriptionService prescriptionService;


    /**
     * Handles the view for displaying all registered pets for the currently logged-in user.
     */
    @GetMapping
    public String viewRegisteredPets(Model model) {
        // Get the currently authenticated user
        CustomUser currentUser = customUserDetailsService.getCurrentUser();

        // Fetch the pets associated with the current user
        List<PetDTO> pets = petService.findPetsByUserId(currentUser.getUserId());

        // Add the list of pets to the model
        if (!pets.isEmpty()) {
            model.addAttribute("pets", pets);
        } else {
            model.addAttribute("noPetsMessage", "You have no registered pets.");
        }

        // Return the view to display the registered pets
        return "pets/index";
    }

    /**
     * Handles the view for displaying a pet's medical history, vaccinations, treatment plans, and prescriptions.
     * @param petId - the ID of the pet whose medical history to display
     */
    @GetMapping("/{petId}/view")
    public String viewPetMedicalHistory(@PathVariable Long petId, Model model) {
        // Fetch the pet by ID - necessary for TH template
        Optional<Pet> pet = petService.findPetBypetId(petId);

        if (pet.isEmpty()) {
            model.addAttribute("errorMessage", "Pet not found.");
            return "pets/error";
        }

        // Add the pet to the model
        model.addAttribute("pet", pet.get());

        // Get the pet's medical data
        List<VaccinationRecord> vaccinations = petMedicalHistoryService.getVaccinationRecordsBypetId(petId);
        List<TreatmentPlan> treatmentPlans = petMedicalHistoryService.getTreatmentPlansBypetId(petId);
        List<Prescription> prescriptions = petMedicalHistoryService.getPrescriptionsBypetId(petId);

        // Check if all medical data is empty or not
        if (vaccinations.isEmpty()) {
            model.addAttribute("noVaccinationsMessage", "No vaccination records found.");
        } else {
            model.addAttribute("vaccinations", vaccinations);
        }

        if (treatmentPlans.isEmpty()) {
            model.addAttribute("noTreatmentPlansMessage", "No treatment plans found.");
        } else {
            model.addAttribute("treatmentPlans", treatmentPlans);
        }

        if (prescriptions.isEmpty()) {
            model.addAttribute("noPrescriptionsMessage", "No prescriptions found.");
        } else {
            model.addAttribute("prescriptions", prescriptions);
        }

        // Return the view for displaying the medical data
        return "pets/view";
    }
    
    @GetMapping("/new")
    public String showPetRegistrationForm(Model model) {
        return "pets/new";
    }

    @PostMapping("/new")
    public String registerNewPet(@ModelAttribute("pet") Pet pet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pets/new";
        }

        // Get the currently authenticated user
        CustomUser currentUser = customUserDetailsService.getCurrentUser();
        
        // Set the owner of the pet
        pet.setOwner(currentUser);

        // Save the pet
        petService.savePet(pet);

        // Redirect to the pets list page after saving
        return "redirect:/pets";
    }

    // Handles prescription refill requests
    @PostMapping("/{petId}/refill/{prescriptionID}")
    public String requestPrescription(@PathVariable("petId") Long petId, @PathVariable("prescriptionID") Long prescriptionID, RedirectAttributes redirectAttributes) {

        // Check if prescription is valid
        if (prescriptionService.checkPrescription(prescriptionID)) {
            System.out.println("Processed valid refill request");
            // Decrement and order prescription
            prescriptionService.decrementPrescription(prescriptionID);
            prescriptionService.orderPrescription(prescriptionID, petId);
            // Add success message to redirect
            redirectAttributes.addFlashAttribute("successMessage", "Prescription ordered successfully!");
        } else {
            System.out.println("Invalid refill request");
            // Add fail message to redirect
            redirectAttributes.addFlashAttribute("failMessage", "Prescription failed to order...");
        }

        // Redirect back to same page.
        return "redirect:/pets/{petId}/view";
    }
}
