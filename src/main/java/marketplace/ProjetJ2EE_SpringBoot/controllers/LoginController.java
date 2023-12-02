package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.Functions.Password;
import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.model.Administrateur;
import marketplace.ProjetJ2EE_SpringBoot.service.AdministrateurService;
import marketplace.ProjetJ2EE_SpringBoot.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final ClientService clientService;
    private final AdministrateurService administrateurService;

    public LoginController(ClientService clientService, AdministrateurService administrateurService) {
        this.clientService = clientService;
        this.administrateurService = administrateurService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam String pseudo, @RequestParam String password, HttpServletRequest request, Model model) {
        String message = "";

        try {
            Client client = clientService.findByUsername(pseudo);
            if (client != null && clientService.getPasswordById(client.getIdUtilisateur()).equals(Password.hashPassword(password))) {
                HttpSession session = request.getSession(true);
                session.setAttribute("pseudo", pseudo);
                session.setAttribute("client", client);
                session.setAttribute("role", "client");
                return "redirect:/account";
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        try {
            Administrateur administrateur = administrateurService.findByUsername(pseudo);
            if (administrateur != null && administrateurService.getPasswordById(administrateur.getIdUtilisateur()).equals(Password.hashPassword(password))) {
                HttpSession session = request.getSession(true);
                session.setAttribute("role", "admin");
                return "redirect:/account";
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        message = "Le nom d'utilisateur ou le mot de passe est incorrect. Veuillez réessayer.";
        model.addAttribute("message", message);
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        String message = "";
        model.addAttribute("message", message);
        return "login";
    }
}
