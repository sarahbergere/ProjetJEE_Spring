package marketplace.ProjetJ2EE_SpringBoot.repository;

import marketplace.ProjetJ2EE_SpringBoot.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    @Modifying
    @Query("UPDATE Paiement p SET p.compteBancaireID = null WHERE p.compteBancaireID = :id")
    void setPaiementToNullById(@Param("id") int id);
}
