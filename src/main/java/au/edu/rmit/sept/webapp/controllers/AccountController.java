package au.edu.rmit.sept.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @GetMapping("/account")
    public String viewAccountInformation(Model model) {
        return "account/index";
    }

    @GetMapping("/account")
    public String updatePersonalInformation() {
        return "account/settings";
    }

    @GetMapping("/appointments")
    public String viewAppointments()
    {
        return "appointments/index";
    }

    @GetMapping("/pets")
    public String registerNewPet()
    {
        return "pets/new";
    }

    @GetMapping("/pets")
    public String viewPetInformation()
    {
        return "pets/info";
    }

    @GetMapping("/account")
    public String viewSavedResources()
    {
        return "account/savededuresources";
    }
}

