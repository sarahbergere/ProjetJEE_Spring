package marketplace.ProjetJ2EE_SpringBoot.service;

import marketplace.ProjetJ2EE_SpringBoot.repository.DetailCommandeRepository;
import marketplace.ProjetJ2EE_SpringBoot.model.DetailCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetailCommandeService {

    private final DetailCommandeRepository detailCommandeRepository;

    @Autowired
    public DetailCommandeService(DetailCommandeRepository detailCommandeRepository) {
        this.detailCommandeRepository = detailCommandeRepository;
    }

    public void createDetailCommande(DetailCommande detailCommande) {
        detailCommandeRepository.save(detailCommande);
    }

    public void saveDetailCommande(DetailCommande detailCommande) {
        detailCommandeRepository.save(detailCommande);
    }

    public DetailCommande findDetailCommandeById(Long id) {
        return detailCommandeRepository.findById(id).orElse(null);
    }

    public List<DetailCommande> findAllDetailCommandes() {
        return detailCommandeRepository.findAll();
    }

    public void updateDetailCommande(DetailCommande detailCommande) {
        detailCommandeRepository.save(detailCommande);
    }

    public void deleteDetailCommande(Long id) {
        detailCommandeRepository.deleteById(id);
    }

    public List<DetailCommande> findDetailCommandesByCommandeId(int commandeId) {
        return detailCommandeRepository.findByCommande_Id(commandeId);
    }
    @Transactional
    public void mettreAJourProduitId(int id) {
        detailCommandeRepository.setProduitToNullById(id);
    }
}
