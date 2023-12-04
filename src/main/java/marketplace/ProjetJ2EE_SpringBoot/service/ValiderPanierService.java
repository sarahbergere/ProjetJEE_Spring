package marketplace.ProjetJ2EE_SpringBoot.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class ValiderPanierService {

    public String validerPanier(HttpSession session) {
        String errorMessage = "";

        boolean clientConnecte = (session.getAttribute("client") != null);
        boolean admin = "admin".equals(session.getAttribute("role"));

        if (admin) {
            errorMessage = "Vous êtes administrateur, vous ne pouvez pas acheter de produits.";
        } else if (clientConnecte) {
            errorMessage = "redirect:/paiement";
        } else {
            errorMessage = "Vous devez être connecté pour valider votre panier. Connectez-vous <a href='/login'>ici</a>";
        }

        return errorMessage;
    }
}

