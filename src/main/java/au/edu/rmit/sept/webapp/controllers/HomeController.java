package au.edu.rmit.sept.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home(Model model) {
    return "home/index.html";
  }

  @GetMapping("/vet")
  public String vetHome()
  {
    return "vet/index";
  }

  @GetMapping("/admin")
  public String adminHome()
  {
    return "admin/index";
  }
  
  @GetMapping("/pets")
  public String viewPets() {
      return "pets/index";
  }

  @GetMapping("/eduresources")
  public String viewResources() {
      return "eduresources/index";
  }

  @GetMapping("/account/settings")
  public String viewAccountSettings() {
      return "account/settings";
  }

  @GetMapping("/about")
  public String viewAbout() {
      return "home/about";
  }
}
