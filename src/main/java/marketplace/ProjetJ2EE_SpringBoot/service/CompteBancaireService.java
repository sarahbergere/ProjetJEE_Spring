package marketplace.ProjetJ2EE_SpringBoot.service;

import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.repository.CompteBancaireRepository;
import marketplace.ProjetJ2EE_SpringBoot.model.CompteBancaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteBancaireService {

    private final CompteBancaireRepository compteBancaireRepository;

    @Autowired
    public CompteBancaireService(CompteBancaireRepository compteBancaireRepository) {
        this.compteBancaireRepository = compteBancaireRepository;
    }

    public int createCompteBancaire(CompteBancaire compteBancaire) {
        CompteBancaire savedCompteBancaire = compteBancaireRepository.save(compteBancaire);
        return savedCompteBancaire.getId();
    }

    public CompteBancaire getCompteBancaireById(int id) {
        return compteBancaireRepository.findById(id).orElse(null);
    }

    public List<CompteBancaire> getAllComptesBancaires() {
        return compteBancaireRepository.findAll();
    }

    public List<CompteBancaire> getAllComptesBancairesByIdClient(Client client) {
        return compteBancaireRepository.findAllByIdClient(client.getId());
    }

    public void updateCompteBancaire(CompteBancaire compteBancaire) {
        compteBancaireRepository.save(compteBancaire);
    }

    public void deleteCompteBancaire(int id) {
        compteBancaireRepository.deleteById(id);
    }
}
