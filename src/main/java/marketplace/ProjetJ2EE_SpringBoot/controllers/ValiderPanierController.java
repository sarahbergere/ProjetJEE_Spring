package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.service.ValiderPanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ValiderPanierController {

    @Autowired
    private ValiderPanierService validerPanierService;

    @PostMapping("/ValiderPanier")
    public String validerPanier(HttpSession session, Model model) {
        String errorMessage = validerPanierService.validerPanier(session);
        session.setAttribute("erreurMessage", errorMessage);
        if (errorMessage.startsWith("redirect:")) {
            session.removeAttribute("erreurMessage");
            return errorMessage;
        } else {
            model.addAttribute("erreurMessage", errorMessage);
            return "redirect:/panier";
        }
    }

    @GetMapping("/ValiderPanier")
    public String validerPanierGet (HttpSession session, Model model){
        return "redirect:/panier";
    }
}
