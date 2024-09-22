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

    private Long getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();  
        // System.out.println("Authenticated user username: " + username); //checking the logged user
        return userService.getUserIdByUsername(username);
    }
     
    @GetMapping("/settings")
    public String accountSettings(Model model) {
        // Long userID = 6L;  // Use a fixed user ID for testing
        Long userID = getAuthenticatedUserId();  
        userService.getUserById(userID).ifPresent(user -> model.addAttribute("user", user));
        return "account/settings";
    }

    @PostMapping("/update-contact-info")
    public String updateContactInfo(@ModelAttribute("user") User updatedUser, Model model) {
        // Long userID = 6L;  // Simulate logged-in user with ID 1
        Long userID = getAuthenticatedUserId();  
        userService.updateContactInfo(userID, updatedUser);
        model.addAttribute("successMessage", "Contact information updated successfully.");
        return "redirect:/account/settings";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, 
                                 @RequestParam("newPassword") String newPassword, 
                                 Model model) {
        // Long userID = 6L;  // Simulate logged-in user with ID 1
        Long userID = getAuthenticatedUserId();  
        if (userService.changePassword(userID, oldPassword, newPassword)) {
            model.addAttribute("successMessage", "Password changed successfully.");
            return "redirect:/account/settings";
        } else {
            model.addAttribute("errorMessage", "Invalid current password.");
            return "account/settings";
        }
    }
    
    @PostMapping("/delete-account")
    public String deleteAccount(@RequestParam("password") String password, Model model) {
        // Long userID = 6L;  // Simulate logged-in user with ID 1
        Long userID = getAuthenticatedUserId();  
        if (userService.deleteAccount(userID, password)) {
            return "redirect:/logout";  
        } else {
            model.addAttribute("errorMessage", "Invalid password. Account deletion failed.");
            return "account/settings";
        }
    }
}

