package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.model.Produit;
import marketplace.ProjetJ2EE_SpringBoot.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BienvenueController {

    private final ProduitService produitService;

    public BienvenueController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/bienvenue")
    public String afficherMarketplace(Model model) {
        List<Produit> produitsPopulaires = produitService.getProduitsPopulaires();
        model.addAttribute("produits", produitsPopulaires);
        return "bienvenue";
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Produit> produitsPopulaires = produitService.getProduitsPopulaires();
        model.addAttribute("produits", produitsPopulaires);
        return "bienvenue";
    }
}
