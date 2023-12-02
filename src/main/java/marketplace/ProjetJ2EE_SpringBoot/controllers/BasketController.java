package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

import static marketplace.ProjetJ2EE_SpringBoot.Functions.PanierUtil.calculateTotalAmount;

@Controller
public class BasketController {

    private final ProduitService produitService;

    public BasketController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/panier")
    public String afficherPanier(HttpSession session, Model model) {
        Map<Integer, Integer> panier = (Map<Integer, Integer>) session.getAttribute("panier");
        model.addAttribute("panier", panier);
        model.addAttribute("produitService", produitService);

        if (panier != null && !panier.isEmpty()) {
            double totalAmount = calculateTotalAmount(panier,produitService);
            model.addAttribute("totalAmount", totalAmount);
        }

        String erreurMessage = (String) session.getAttribute("erreurMessage");
        if (Objects.nonNull(erreurMessage)) {
            model.addAttribute("erreurMessage", erreurMessage);
            session.removeAttribute("erreurMessage");
        }

        return "panier";
    }
}
