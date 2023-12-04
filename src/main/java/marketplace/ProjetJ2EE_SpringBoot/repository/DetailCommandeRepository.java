package marketplace.ProjetJ2EE_SpringBoot.repository;

import marketplace.ProjetJ2EE_SpringBoot.model.DetailCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailCommandeRepository extends JpaRepository<DetailCommande, Long> {

    @Query("SELECT dc FROM DetailCommande dc WHERE dc.commande.id = :commandeId")
    List<DetailCommande> findByCommande_Id(@Param("commandeId") int commandeId);

}
