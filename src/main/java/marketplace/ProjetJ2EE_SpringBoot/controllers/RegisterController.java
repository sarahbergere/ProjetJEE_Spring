package marketplace.ProjetJ2EE_SpringBoot.controllers;

import marketplace.ProjetJ2EE_SpringBoot.Functions.Password;
import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.model.Droit;
import marketplace.ProjetJ2EE_SpringBoot.model.Role;
import marketplace.ProjetJ2EE_SpringBoot.model.Utilisateur;
import marketplace.ProjetJ2EE_SpringBoot.service.ClientService;
import marketplace.ProjetJ2EE_SpringBoot.service.EmailService;
import marketplace.ProjetJ2EE_SpringBoot.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@Controller
public class RegisterController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ClientService clientService;

    @PostMapping("/inscription")
    public String registerUser(@RequestParam String pseudo,
                               @RequestParam String nom,
                               @RequestParam String prenom,
                               @RequestParam String adresse,
                               @RequestParam String email,
                               @RequestParam String telephone,
                               @RequestParam String password,
                               Model model
    ) {
        String message = "";

        if (pseudo == null || nom == null || prenom == null || adresse == null || email == null || telephone == null || password == null ||
                pseudo.isEmpty() || nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || email.isEmpty() || telephone.isEmpty() || password.isEmpty()) {
            message = "Veuillez remplir tous les champs du formulaire.";
            model.addAttribute("message", message);
            return "inscription";
        } else {
            if (utilisateurService.findByUsername(pseudo) == null) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setPseudo(pseudo);
                try {
                    utilisateur.setMotDePasse(Password.hashPassword(password));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                utilisateur.setRole(Role.client);

                int utilisateurid = utilisateurService.createUtilisateur(utilisateur);
                Utilisateur utilisateur1 = utilisateurService.findUtilisateurById(utilisateurid);

                if (utilisateur1 != null) {
                    Client nouveauClient = new Client(nom, prenom, adresse, email, telephone, Droit.aucun.toString());
                    nouveauClient.setIdUtilisateur(utilisateur1.getId());

                    clientService.createClient(nouveauClient);
                    emailService.sendEmail(nouveauClient.getEmail(),"Confirmation de la création de votre compte","Bonjour "+nouveauClient.getPrenom()+",\n\n" +
                            "Votre compte a été créé avec succès. Nous sommes ravis de vous accueillir dans notre communauté.\n" +
                            "\n" +
                            "N'hésitez pas à explorer notre site/application et à profiter de tous nos services. Si vous avez des questions ou des préoccupations, n'hésitez pas à nous contacter.\n" +
                            "\n" +
                            "Merci de faire partie de notre communauté !\n" +
                            "\n" +
                            "Cordialement,\n" +
                            "Notre Marketplace\n" );

                    message = "Le compte a été créé avec succès. Un e-mail de confirmation a été envoyé à " + email;
                    model.addAttribute("message", message);
                    return "inscription";
                } else {
                    message = "Erreur lors de la création du compte. Veuillez réessayer.";
                    model.addAttribute("message", message);
                    return "inscription";
                }
            } else {
                message = "Pseudonyme déjà utilisé.";
                model.addAttribute("message", message);
                return "inscription";
            }
        }
    }

    @GetMapping("/inscription")
    public String showRegistrationPage(Model model) {
        return "inscription";
    }
}

