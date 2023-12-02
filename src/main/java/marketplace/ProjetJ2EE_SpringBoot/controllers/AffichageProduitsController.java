package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.model.Produit;
import marketplace.ProjetJ2EE_SpringBoot.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AffichageProduitsController {
        private final ProduitService produitService;

        @Autowired
        public AffichageProduitsController(ProduitService produitService) {
            this.produitService = produitService;
        }

        @GetMapping("/affichageProduit")
        public String afficherProduits(Model model) {
            List<Produit> produits = produitService.findAllProduits();
            model.addAttribute("produits", produits);
            return "affichageProduit";
        }
}
