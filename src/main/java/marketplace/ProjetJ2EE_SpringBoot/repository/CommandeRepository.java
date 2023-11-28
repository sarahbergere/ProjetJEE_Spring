package marketplace.ProjetJ2EE_SpringBoot.repository;// CommandeRepository.java
import marketplace.ProjetJ2EE_SpringBoot.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    List<Commande> findAllByClient_Id(int clientId);
}
