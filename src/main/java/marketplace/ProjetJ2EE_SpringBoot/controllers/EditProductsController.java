package marketplace.ProjetJ2EE_SpringBoot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.model.Produit;
import marketplace.ProjetJ2EE_SpringBoot.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EditProductsController {

    @Autowired
    private ProduitService produitService;

    @GetMapping("/editProducts")
    public String showProducts(Model model, HttpServletRequest request) {
        if(!isConnected(request.getSession())){
            return "redirect:/account";
        }
        HttpSession session = request.getSession(false);
        Client client = (Client) session.getAttribute("client");

        String droit = (client != null) ? client.getDroit() : (String) session.getAttribute("role");

        if ("ajout".equals(droit) || "tout".equals(droit) || "admin".equals(droit)) {
            model.addAttribute("canAddProduct", true);
        }

        List<Produit> produits = produitService.findAllProduits();
        model.addAttribute("produits", produits);
        model.addAttribute("droit", droit);

        return "editProducts";
    }

    private boolean isConnected(HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        return ((client != null && !client.getDroit().equals("aucun")) || (session.getAttribute("role") != null && session.getAttribute("role").equals("admin")));
    }
}
