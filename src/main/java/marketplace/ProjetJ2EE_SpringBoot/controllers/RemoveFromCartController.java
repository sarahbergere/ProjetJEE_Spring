package marketplace.ProjetJ2EE_SpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class RemoveFromCartController {

    @PostMapping("/RemoveFromCart")
    public String removeFromCart(@RequestParam("productId") int productId,
                                 HttpSession session,
                                 Model model) {

        // Récupérer le panier depuis la session
        Map<Integer, Integer> panier = (Map<Integer, Integer>) session.getAttribute("panier");
        if (panier != null && panier.containsKey(productId)) {
            panier.remove(productId);

            session.setAttribute("panier", panier);

            return "redirect:/panier";
        } else {
                model.addAttribute("errorMessage", "Produit non trouvé dans le panier.");
                return "error";
        }
    }
}
