package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.model.Droit;
import marketplace.ProjetJ2EE_SpringBoot.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showClientPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("role") != null && session.getAttribute("role").equals("client")) {
            Client client = (Client) session.getAttribute("client");

            String modifierProduit = client.getDroit().equals(Droit.aucun.toString()) ? "" : "<hr><div>\n" +
                    "        <h2>Vos droits</h2><a href=\"editProducts\"><button>Modifier Produit</button></a></p>\n" +
                    "    </div>";

            session.setAttribute("modifierProduit", modifierProduit);
            clientService.chargerCompteBancaire(client);
            clientService.chargerCommande(client);

            return "client";
        } else {
            return "redirect:/login";
        }
    }
}
