package marketplace.ProjetJ2EE_SpringBoot.service;

import marketplace.ProjetJ2EE_SpringBoot.repository.AdministrateurRepository;
import marketplace.ProjetJ2EE_SpringBoot.model.Administrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurService {

    private final AdministrateurRepository administrateurRepository;

    @Autowired
    public AdministrateurService(AdministrateurRepository administrateurRepository) {
        this.administrateurRepository = administrateurRepository;
    }

    public Administrateur findById(Long id) {
        return administrateurRepository.findById(id).orElse(null);
    }

    public List<Administrateur> findAll() {
        return administrateurRepository.findAll();
    }

    public Administrateur findByIdUsername(int id) {
        return administrateurRepository.findByIdUtilisateur(id);
    }

    public void saveOrUpdate(Administrateur administrateur) {
        administrateurRepository.save(administrateur);
    }

    public void delete(Administrateur administrateur) {
        administrateurRepository.delete(administrateur);
    }
}
