package marketplace.ProjetJ2EE_SpringBoot.service;

import marketplace.ProjetJ2EE_SpringBoot.repository.ProduitRepository;
import marketplace.ProjetJ2EE_SpringBoot.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public void saveProduit(Produit produit) {
        produitRepository.save(produit);
    }

    public Produit findProduitById(Integer id) {
        return produitRepository.findById(id).orElse(null);
    }

    public List<Produit> findAllProduits() {
        return produitRepository.findAll();
    }

    public void updateProduit(Produit produit) {
        produitRepository.save(produit);
    }

    public void deleteProduit(Integer id) {
        produitRepository.deleteById(id);
    }

    public List<Produit> getProduitsPopulaires() {
        return produitRepository.findRandomProducts(4);
    }
}

