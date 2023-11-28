package marketplace.ProjetJ2EE_SpringBoot.repository;

import marketplace.ProjetJ2EE_SpringBoot.model.DetailCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailCommandeRepository extends JpaRepository<DetailCommande, Long> {

    List<DetailCommande> findByCommande_Id(int commandeId);

}
