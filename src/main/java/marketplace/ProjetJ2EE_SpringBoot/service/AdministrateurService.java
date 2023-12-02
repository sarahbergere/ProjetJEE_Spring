package marketplace.ProjetJ2EE_SpringBoot.service;

import marketplace.ProjetJ2EE_SpringBoot.model.Administrateur;
import marketplace.ProjetJ2EE_SpringBoot.repository.AdministrateurRepository;
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

    public Administrateur findById(int id) {
        return administrateurRepository.findById(id).orElse(null);
    }

    public List<Administrateur> findAll() {
        return administrateurRepository.findAll();
    }

    public void saveOrUpdate(Administrateur administrateur) {
        administrateurRepository.save(administrateur);
    }

    public void delete(Administrateur administrateur) {
        administrateurRepository.delete(administrateur);
    }

    public Administrateur findByUsername(String username) {
        return administrateurRepository.findByUsername(username).orElse(null);
    }

    public String getPasswordById(int adminId) {
        return administrateurRepository.getPasswordById(adminId);
    }
}
