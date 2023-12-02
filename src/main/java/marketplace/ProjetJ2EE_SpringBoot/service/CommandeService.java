package marketplace.ProjetJ2EE_SpringBoot.service;

import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.repository.CommandeRepository;
import marketplace.ProjetJ2EE_SpringBoot.model.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public int createCommande(Commande commande) {
        Commande savedCommande = commandeRepository.save(commande);
        return savedCommande.getId();
    }

    public Commande getCommandeById(int id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public List<Commande> getAllCommandesByIdClient(Client client) {
        return commandeRepository.findAllByIdClient(client.getId());
    }

    public void updateCommande(Commande commande) {
        commandeRepository.save(commande);
    }

    public void deleteCommande(int id) {
        commandeRepository.deleteById(id);
    }
}
