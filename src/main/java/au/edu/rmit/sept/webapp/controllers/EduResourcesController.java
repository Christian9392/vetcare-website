package au.edu.rmit.sept.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EduResourcesController {

    @GetMapping("/eduresources")
    public String viewResources(Model model) {
        return "eduresources/index";
    }

    @GetMapping("/eduresources")
    public String viewResourceInformation() {
        return "eduresources/info";
    }

    @GetMapping("/eduresources")
    public String viewSavedResources()
    {
        return "eduresources/savededuresources";
    }
}