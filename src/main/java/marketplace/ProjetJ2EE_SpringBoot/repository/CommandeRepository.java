package marketplace.ProjetJ2EE_SpringBoot.repository;// CommandeRepository.java
import marketplace.ProjetJ2EE_SpringBoot.model.Commande;
import marketplace.ProjetJ2EE_SpringBoot.model.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    @Query("SELECT c FROM Commande c WHERE c.client.id = :clientId")
    List<Commande> findAllByIdClient(@Param("clientId") int clientId);
}
