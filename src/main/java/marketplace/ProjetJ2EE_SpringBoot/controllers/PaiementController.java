package marketplace.ProjetJ2EE_SpringBoot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import marketplace.ProjetJ2EE_SpringBoot.Functions.PanierUtil;
import marketplace.ProjetJ2EE_SpringBoot.model.*;
import marketplace.ProjetJ2EE_SpringBoot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class PaiementController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private PaiementService paiementService;

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private DetailCommandeService detailCommandeService;

    @Autowired
    private CompteBancaireService compteBancaireService;

    @PostMapping("/ProcessPayment")
    public String processPayment(@RequestParam("nom") String nom,
            @RequestParam("adresse") String adresse,
            @RequestParam("ville") String ville,
            @RequestParam("codePostal") String codePostal,
            @RequestParam("pays") String pays,
            @RequestParam(value = "optionCompte", required = false) String optionCompte,
            @RequestParam(value = "compteBancaire", required = false) Integer compteBancaireId,
            @RequestParam(value = "titulaire", required = false) String titulaire,
            @RequestParam(value = "numeroCompte", required = false) String numeroCompte,
            @RequestParam(value = "solde", required = false) Double solde,
            HttpServletRequest request) throws IOException {
        if(!isConnected(request.getSession())){
            return "redirect:/bienvenue";
        }
        HttpSession session = request.getSession(false);
        String messageErreur = "";
        int idCompteBancaire = 0;

        double montantPanier = PanierUtil.calculateTotalAmount((Map<Integer, Integer>) session.getAttribute("panier"), produitService);


        CompteBancaire compteBancaire = null;
        Date date = new Date();

        if ("choisirCompteLie".equals(optionCompte)) { // Compte déjà dans la base de données
            compteBancaire = compteBancaireService.getCompteBancaireById(compteBancaireId);
            if (compteBancaire.getSolde() < montantPanier) {
                messageErreur = "Le solde du compte choisi est inférieur au montant de votre panier. Veuillez choisir un autre compte bancaire.";
                session.setAttribute("messageErreur", messageErreur);
                return "forward:/paiement.jsp";
            }
            compteBancaire.setSolde(compteBancaire.getSolde() - montantPanier);
            compteBancaireService.updateCompteBancaire(compteBancaire);
            idCompteBancaire = compteBancaireId;
        } else if ("lierCompte".equals(optionCompte)) {
            double nouveauSolde = Double.parseDouble(solde.toString());
            if (nouveauSolde < montantPanier) {
                messageErreur = "Le solde de votre compte est inférieur au montant de votre panier. Veuillez choisir un autre compte bancaire.";
                session.setAttribute("messageErreur", messageErreur);
                return "forward:/paiement.jsp";
            }

            compteBancaire = new CompteBancaire(titulaire, numeroCompte, nouveauSolde - montantPanier, (Client) session.getAttribute("client"));
            idCompteBancaire = compteBancaireService.createCompteBancaire(compteBancaire);
        }

        Commande commande = new Commande((Client) session.getAttribute("client"), date, StatutCommande.traitement.toString(), montantPanier, nom, adresse, codePostal, ville, pays);
        int idCommande = commandeService.createCommande(commande);
        session.setAttribute("commande", commande);

        if (idCommande != 0) {
            Paiement paiement = new Paiement(idCommande, idCompteBancaire, montantPanier, date);
            paiementService.createPaiement(paiement);

            Map<Integer, Integer> panier = (Map<Integer, Integer>) session.getAttribute("panier");
            List<DetailCommande> detailsCommandeList = new ArrayList<>();

            for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();

                DetailCommande detailCommande = new DetailCommande(commande, produitService.findProduitById(productId), quantity);
                detailCommandeService.createDetailCommande(detailCommande);

                Produit produit = produitService.findProduitById(productId);
                produit.setStock(produit.getStock() - quantity);
                produitService.updateProduit(produit);

                detailsCommandeList.add(detailCommande);
            }

            session.setAttribute("detailsCommande", detailsCommandeList);
        }

        Client client = (Client) session.getAttribute("client");

        StringBuilder message = new StringBuilder();
        message.append("Bonjour ").append(client.getPrenom()).append(",\n\n")
                .append("Nous vous confirmons la réception de votre commande.\n\n")
                .append("Détails de la commande:\n")
                .append("Numéro de commande: ").append(commande.getId()).append("\n")
                .append("Date de commande: ").append(commande.getDateDeCommande()).append("\n")
                .append("Montant de la commande: ").append(commande.getMontant()).append(" €\n\n")
                .append("Produits commandés:\n");


        List<DetailCommande> detailsCommande = detailCommandeService.findDetailCommandesByCommandeId(commande.getId());

        for (DetailCommande detailCommande : detailsCommande) {
            Produit produit = produitService.findProduitById(detailCommande.getProduit().getId());

            message.append("- ").append(produit.getNom()).append(" (Quantité: ").append(detailCommande.getQuantite()).append(")\n");
        }

        message.append("\nMerci de votre confiance !\n\n")
                .append("Cordialement,\n")
                .append("MarketPlace\n");
        emailService.sendEmail(client.getEmail(),"Confirmation de commande", message.toString() );


        session.removeAttribute("panier");
        return "redirect:/confirmation";
    }

    @GetMapping("/paiement")
    public String afficherPagePaiement(Model model, HttpServletRequest request) {
        if(!isConnected(request.getSession())){
            return "redirect:/bienvenue";
        }
        HttpSession session = request.getSession(false);
        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            model.addAttribute("client", client);
        }

        return "paiement";
    }

    @GetMapping("/confirmation")
    public String afficherConfirmationCommande(HttpServletRequest request, Model model) {
        if(!isConnected(request.getSession())){
            return "redirect:/bienvenue";
        }
        HttpSession session = request.getSession(false);
        Commande commande = (Commande) session.getAttribute("commande");
        List<DetailCommande> detailsCommande = (List<DetailCommande>) session.getAttribute("detailsCommande");

        model.addAttribute("commande", commande);
        model.addAttribute("detailsCommande", detailsCommande);

        return "confirmation";
    }

    private boolean isConnected(HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        return ((client != null && session.getAttribute("panier") != null));
    }
}