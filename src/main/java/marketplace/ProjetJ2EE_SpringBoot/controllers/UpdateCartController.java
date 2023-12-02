package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.model.Produit;
import marketplace.ProjetJ2EE_SpringBoot.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.Map;

@Controller
public class UpdateCartController {

    @Autowired
    private ProduitService produitService;

    @PostMapping("/UpdateCart")
    public String updateCart(@RequestParam("productId") int productId,
                             @RequestParam("quantity") int newQuantity,
                             HttpSession session,
                             Model model) {

        Produit produit = produitService.findProduitById(productId);

        Map<Integer, Integer> panier = (Map<Integer, Integer>) session.getAttribute("panier");

        if (panier != null && panier.containsKey(productId)) {
            if (newQuantity > produit.getStock()) {
                newQuantity = produit.getStock();
            }
            panier.put(productId, newQuantity);
            session.setAttribute("panier", panier);

            return "redirect:/panier";
        } else {
            model.addAttribute("errorMessage", "Produit non trouvé dans le panier.");
            return "error";
        }
    }
}
