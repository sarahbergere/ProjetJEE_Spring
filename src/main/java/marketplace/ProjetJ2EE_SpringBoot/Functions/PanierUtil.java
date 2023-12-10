package marketplace.ProjetJ2EE_SpringBoot.Functions;

import marketplace.ProjetJ2EE_SpringBoot.service.ProduitService;
import marketplace.ProjetJ2EE_SpringBoot.model.Produit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PanierUtil {

    // Méthode pour calculer le total du panier
    public static double calculateTotalAmount(Map<Integer, Integer> panier, ProduitService produitService) {
        BigDecimal total = BigDecimal.ZERO;

        if (panier != null && !panier.isEmpty()) {
            for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();

                // Récupérez le produit à partir du service
                Produit produit = produitService.findProduitById(productId);

                // Ajoutez le montant total pour ce produit à la somme totale
                BigDecimal productTotal = BigDecimal.valueOf(produit.getPrix() * quantity);
                total = total.add(productTotal);
            }
        }
        total = total.setScale(2, RoundingMode.HALF_UP);
        return total.doubleValue();
    }
}
