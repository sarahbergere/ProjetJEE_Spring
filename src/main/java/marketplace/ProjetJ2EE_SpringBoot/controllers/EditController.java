package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.model.Produit;
import marketplace.ProjetJ2EE_SpringBoot.service.DetailCommandeService;
import marketplace.ProjetJ2EE_SpringBoot.service.ProduitService;
import org.springframework.boot.Banner;
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
    public String editProductPage(@PathVariable String id, Model model) {
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
            Model model
    ) {
        produitService.saveProduit(Integer.parseInt(id),nom,Double.parseDouble(price),description,Integer.parseInt(stock),imageUrl);
        return "redirect:/product/edit/" + id ;
    }

    @GetMapping("/delete/{idProduit}")
    public String supprimerProduit(@PathVariable int idProduit, Model model) {
        detailCommandeService.mettreAJourProduitId(idProduit);

        int rowsAffected = produitService.deleteProduit(idProduit);
        model.addAttribute("rowsAffected", rowsAffected);
        return "/deleteProduct";
    }

    @GetMapping("/create")
    public String creerProduit(){

        return "addProduct";
    }

    @PostMapping("/create")
    public String ajouterProduit(
            @RequestParam String nom,
            @RequestParam double prix,
            @RequestParam String description,
            @RequestParam int stock,
            @RequestParam String image,
            Model model
    ) {
        Produit nouveauProduit = new Produit();
        nouveauProduit.setDescription(description);
        nouveauProduit.setPrix(prix);
        nouveauProduit.setNom(nom);
        nouveauProduit.setImage(image);
        nouveauProduit.setStock(stock);
        produitService.saveProduit(nouveauProduit);

        return "redirect:/editProducts";
    }
}
