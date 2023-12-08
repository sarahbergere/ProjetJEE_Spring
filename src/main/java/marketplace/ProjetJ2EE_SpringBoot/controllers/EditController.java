package marketplace.ProjetJ2EE_SpringBoot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.model.Produit;
import marketplace.ProjetJ2EE_SpringBoot.service.DetailCommandeService;
import marketplace.ProjetJ2EE_SpringBoot.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class EditController {
    private final ProduitService produitService;
    private final DetailCommandeService detailCommandeService;

    public EditController(ProduitService produitService, DetailCommandeService detailCommandeService) {
        this.produitService = produitService; this.detailCommandeService = detailCommandeService;}

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model, HttpServletRequest request) {
        if(!isConnected(request.getSession())){
            return "redirect:/account";
        }
        Produit product = produitService.findProduitById(Integer.parseInt(id));

        model.addAttribute("product", product);
        return "edit";
    }

    @PostMapping("/update")
    public String updateProduct(
            @RequestParam String id,
            @RequestParam String nom,
            @RequestParam String price,
            @RequestParam String description,
            @RequestParam String stock,
            @RequestParam String imageUrl,
            HttpServletRequest request
    ) {
        if(!isConnected(request.getSession())){
            return "redirect:/account";
        }
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setId(Integer.parseInt(id));
        produit.setPrix(Double.parseDouble(price));
        produit.setDescription(description);
        produit.setStock(Integer.parseInt(stock));
        produit.setImage(imageUrl);

        produitService.saveProduit(produit);
        return "redirect:/product/edit/" + id ;
    }

    @GetMapping("/delete/{idProduit}")
    public String supprimerProduit(@PathVariable int idProduit, Model model, HttpServletRequest request) {
        if(!isConnected(request.getSession())){
            return "redirect:/account";
        }

        detailCommandeService.mettreAJourProduitId(idProduit);

        int rowsAffected = produitService.deleteProduit(idProduit);
        model.addAttribute("rowsAffected", rowsAffected);
        return "deleteProduct";
    }

    @GetMapping("/create")
    public String creerProduit(HttpSession session){
        if(!isConnected(session)){
            return "redirect:/account";
        }
        return "addProduct";
    }

    @PostMapping("/create")
    public String ajouterProduit(
            @RequestParam String nom,
            @RequestParam double prix,
            @RequestParam String description,
            @RequestParam int stock,
            @RequestParam String image,
            HttpServletRequest request
    ) {
        if(!isConnected(request.getSession())){
            return "redirect:/account";
        }
        Produit nouveauProduit = new Produit();
        nouveauProduit.setDescription(description);
        nouveauProduit.setPrix(prix);
        nouveauProduit.setNom(nom);
        nouveauProduit.setImage(image);
        nouveauProduit.setStock(stock);
        produitService.saveProduit(nouveauProduit);

        return "redirect:/editProducts";
    }

    private boolean isConnected(HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        return ((client != null && !client.getDroit().equals("aucun")) || (session.getAttribute("role") != null && session.getAttribute("role").equals("admin")));
    }
}
