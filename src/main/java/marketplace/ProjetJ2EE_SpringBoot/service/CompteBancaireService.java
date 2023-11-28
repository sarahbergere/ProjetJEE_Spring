package marketplace.ProjetJ2EE_SpringBoot.service;

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

    public List<CompteBancaire> getAllComptesBancairesByIdClient(int idClient) {
        return compteBancaireRepository.findAllByClient_Id(idClient);
    }

    public void updateCompteBancaire(CompteBancaire compteBancaire) {
        // Votre logique de mise à jour ici
    }

    public void deleteCompteBancaire(int id) {
        compteBancaireRepository.deleteById(id);
    }
}
