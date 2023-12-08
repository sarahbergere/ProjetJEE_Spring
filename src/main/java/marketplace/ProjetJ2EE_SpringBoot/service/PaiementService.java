package marketplace.ProjetJ2EE_SpringBoot.service;

import marketplace.ProjetJ2EE_SpringBoot.repository.PaiementRepository;
import marketplace.ProjetJ2EE_SpringBoot.model.Paiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaiementService {

    private final PaiementRepository paiementRepository;

    @Autowired
    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public void createPaiement(Paiement paiement) {
        paiementRepository.save(paiement);
    }

    public void savePaiement(Paiement paiement) {
        paiementRepository.save(paiement);
    }

    public Paiement findPaiementById(Long id) {
        return paiementRepository.findById(id).orElse(null);
    }

    public List<Paiement> findAllPaiements() {
        return paiementRepository.findAll();
    }

    public void updatePaiement(Paiement paiement) {
        paiementRepository.save(paiement);
    }

    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }

    @Transactional
    public void mettreAJourPaiementId(int id) {
        paiementRepository.setPaiementToNullById(id);
    }
}
