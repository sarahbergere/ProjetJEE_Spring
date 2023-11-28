package marketplace.ProjetJ2EE_SpringBoot.service;

import marketplace.ProjetJ2EE_SpringBoot.repository.UtilisateurRepository;
import marketplace.ProjetJ2EE_SpringBoot.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public int createUtilisateur(Utilisateur utilisateur) {
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return savedUtilisateur.getId();
    }

    public Utilisateur findUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
