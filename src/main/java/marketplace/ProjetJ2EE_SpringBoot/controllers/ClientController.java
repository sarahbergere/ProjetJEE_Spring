package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.model.CompteBancaire;
import marketplace.ProjetJ2EE_SpringBoot.model.Droit;
import marketplace.ProjetJ2EE_SpringBoot.service.ClientService;
import marketplace.ProjetJ2EE_SpringBoot.service.CompteBancaireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    private final CompteBancaireService compteBancaireService;

    public ClientController(ClientService clientService, CompteBancaireService compteBancaireService) {
        this.clientService = clientService;
        this.compteBancaireService = compteBancaireService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showClientPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("role") != null && session.getAttribute("role").equals("client")) {
            Client client = (Client) session.getAttribute("client");

            String modifierProduit = client.getDroit().equals(Droit.aucun.toString()) ? "" : "<hr><div>\n" +
                    "        <h2>Vos droits</h2><a href=\"/editProducts\"><button>Modifier Produit</button></a></p>\n" +
                    "    </div>";

            session.setAttribute("modifierProduit", modifierProduit);
            clientService.chargerCompteBancaire(client);
            clientService.chargerCommande(client);

            return "client";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/compte")
    public String ajouterCompteBancaire(
            @RequestParam("titulaire") String titulaire,
            @RequestParam("numeroCompte") String numeroCompte,
            @RequestParam("solde") double solde,
            HttpServletRequest request, Model model
    ) {
        HttpSession session = request.getSession(false);
        Client client = (Client) session.getAttribute("client");
        CompteBancaire compteBancaire = new CompteBancaire(titulaire, numeroCompte, solde, client);

        compteBancaireService.createCompteBancaire(compteBancaire);

        client.ajouterCompteBancaire(compteBancaire);

        return "redirect:/client";
    }

    @PostMapping("/supprimeCompte")
    public String supprimerCompte(
            @RequestParam("compteId") String compteBancaireSupp,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        Client client = (Client) session.getAttribute("client");
        compteBancaireService.deleteCompteBancaire(Integer.parseInt(compteBancaireSupp));
        if (client.getComptes() != null) {
            client.getComptes().removeIf(account -> account.getId() == Integer.parseInt(compteBancaireSupp));
            session.setAttribute("client", client);
        }

        return "redirect:/client";
    }
}
