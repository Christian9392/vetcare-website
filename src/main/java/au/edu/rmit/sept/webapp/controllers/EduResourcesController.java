package au.edu.rmit.sept.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import au.edu.rmit.sept.webapp.services.EduResourcesService;
import au.edu.rmit.sept.webapp.models.EduResources;
import java.util.List;

@Controller
public class EduResourcesController {

    private final EduResourcesService service;

    @Autowired
    public EduResourcesController(EduResourcesService service) {
        this.service = service;
    }

    @GetMapping("/eduresources")
    public String viewResources(Model model) {
        //search result
        String searchResult = "";
        model.addAttribute("searchResult", searchResult);

        //find all videos, articles and guides
        List<EduResources> videos = service.findAllResourcesByType("Video");
        List<EduResources> articles = service.findAllResourcesByType("Article");
        List<EduResources> guides = service.findAllResourcesByType("Guide");
        model.addAttribute("videos", videos);
        model.addAttribute("articles", articles);
        model.addAttribute("guides", guides);

        return "eduresources/index";
    }

    // @GetMapping("/eduresources")
    // public String viewResourceInformation() {
    //     return "eduresources/info";
    // }

    // @GetMapping("/eduresources")
    // public String viewSavedResources()
    // {
    //     return "eduresources/savededuresources";
    // }
}