package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModifyRightsController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/ModifyRight")
    public String modifyRight(@RequestParam int clientId, @RequestParam String nouveauDroit) {
        Client client = clientService.findById(clientId);

        if (client != null) {
            client.setDroit(nouveauDroit);
            clientService.saveOrUpdate(client);
        }

        return "redirect:/admin";
    }
}
