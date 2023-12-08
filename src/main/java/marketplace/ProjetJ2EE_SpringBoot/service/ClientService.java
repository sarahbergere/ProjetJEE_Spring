package marketplace.ProjetJ2EE_SpringBoot.service;

import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.model.Commande;
import marketplace.ProjetJ2EE_SpringBoot.model.CompteBancaire;
import marketplace.ProjetJ2EE_SpringBoot.repository.ClientRepository;
import marketplace.ProjetJ2EE_SpringBoot.repository.CommandeRepository;
import marketplace.ProjetJ2EE_SpringBoot.repository.CompteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final CommandeRepository commandeRepository;
    private final CompteBancaireRepository compteBancaireRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, CommandeRepository commandeRepository, CompteBancaireRepository compteBancaireRepository) {
        this.clientRepository = clientRepository;
        this.commandeRepository = commandeRepository;
        this.compteBancaireRepository = compteBancaireRepository;
    }

    public Client findById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public void saveOrUpdate(Client client) {
        clientRepository.save(client);
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }

    public Client findByUsername(String username) {
        return clientRepository.findByUsername(username).orElse(null);
    }

    public String getPasswordById(int clientId) {
        return clientRepository.getPasswordById(clientId);
    }

    public void chargerCommande(Client client) {
        List<Commande> commandes = commandeRepository.findAllByIdClient(client.getId());
        client.setCommandes(commandes);
    }

    public void chargerCompteBancaire(Client client) {
        List<CompteBancaire> comptes = compteBancaireRepository.findAllByIdClient(client.getId());
        client.setComptes(comptes);
    }

    public void createClient(Client nouveauClient) {
        Client savedClient = clientRepository.save(nouveauClient);
    }
}
