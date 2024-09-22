package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;
    // This method retrieves the ID of the currently authenticated user
    private Long getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();  
        // System.out.println("Authenticated user username: " + username); //checking the logged user
        return userService.getUserIdByUsername(username);
    }

    // Handles GET requests to display the account settings page for the authenticated user
    @GetMapping("/settings")
    public String accountSettings(Model model) {
        Long userID = getAuthenticatedUserId();  
        userService.getUserById(userID).ifPresent(user -> model.addAttribute("user", user));
        return "account/settings";
    }

    // Handles POST requests to update the contact information of the authenticated user.
    @PostMapping("/update-contact-info")
    public String updateContactInfo(@ModelAttribute("user") User updatedUser, Model model) {
        Long userID = getAuthenticatedUserId();  
        userService.updateContactInfo(userID, updatedUser);
        model.addAttribute("successMessage", "Contact information updated successfully.");
        return "redirect:/account/settings";
    }

    // Handles POST requests to change the password of the authenticated user.
    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, 
                                 @RequestParam("newPassword") String newPassword, 
                                 Model model) {
        Long userID = getAuthenticatedUserId();  
        if (userService.changePassword(userID, oldPassword, newPassword)) {
            model.addAttribute("successMessage", "Password changed successfully.");
            return "redirect:/account/settings";
        } else {
            model.addAttribute("errorMessage", "Invalid current password.");
            return "account/settings";
        }
    }
    
    // Handles POST requests to delete the account of the authenticated user.    
    @PostMapping("/delete-account")
    public String deleteAccount(@RequestParam("password") String password, Model model) {
        Long userID = getAuthenticatedUserId();  
        if (userService.deleteAccount(userID, password)) {
            return "redirect:/logout";  
        } else {
            model.addAttribute("errorMessage", "Invalid password. Account deletion failed.");
            return "account/settings";
        }
    }
}

