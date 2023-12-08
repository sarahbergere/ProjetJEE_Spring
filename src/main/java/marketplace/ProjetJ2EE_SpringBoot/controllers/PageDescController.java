package marketplace.ProjetJ2EE_SpringBoot.controllers;

import jakarta.servlet.http.HttpSession;
import marketplace.ProjetJ2EE_SpringBoot.model.Produit;
import marketplace.ProjetJ2EE_SpringBoot.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class PageDescController {
    private final ProduitService produitService;

    public PageDescController(ProduitService produitService) {
        this.produitService = produitService;
    }


    @GetMapping("/pageDesc")
    public String showProduitDetails(@RequestParam("idproduct") int idProduit, HttpSession session, Model model) {
        Produit produit = produitService.findProduitById(idProduit);

        if (produit != null) {
            Map<Integer, Integer> panier = (Map<Integer, Integer>) session.getAttribute("panier");
            int quantitePanier = (panier != null && panier.containsKey(idProduit) ? panier.get(idProduit) : 0);

            model.addAttribute("produit", produit);
            model.addAttribute("quantitePanier", quantitePanier);

            return "pageDesc";
        } else {

            return "redirect:/erreur";
        }
    }
}
