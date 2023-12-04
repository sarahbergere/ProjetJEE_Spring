package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    private final ClientService clientService;

    public AdminController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/admin")
    public String afficherClients(Model model) {
        List<Client> clients = clientService.findAll();

        model.addAttribute("clients", clients);

        return "admin";
    }
}
