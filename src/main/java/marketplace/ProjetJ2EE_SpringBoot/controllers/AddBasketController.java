package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.model.Produit;
import marketplace.ProjetJ2EE_SpringBoot.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AddBasketController {

    private final ProduitService produitService;

    public AddBasketController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping("/AddBasket")
    public String addToBasket(@RequestParam("productId") Integer productId,
                              @RequestParam("quantity") Integer quantity,
                              HttpSession session) {

        Map<Integer, Integer> panier = (Map<Integer, Integer>) session.getAttribute("panier");

        if (panier == null) {
            panier = new HashMap<>();
            session.setAttribute("panier", panier);
        }

        Produit produit = produitService.findProduitById(productId);

        if (produit != null) {
            int stockDisponible = produit.getStock();

            if (panier.containsKey(productId)) {
                int existingQuantity = panier.get(productId);
                if (existingQuantity + quantity > stockDisponible) {
                    panier.put(productId, stockDisponible);
                } else {
                    panier.put(productId, existingQuantity + quantity);
                }
            } else {
                if (quantity > stockDisponible) {
                    panier.put(productId, stockDisponible);
                } else {
                    panier.put(productId, quantity);
                }
            }

            return "redirect:/pageDesc";
        } else {
            return "redirect:/erreur";
        }
    }
}
