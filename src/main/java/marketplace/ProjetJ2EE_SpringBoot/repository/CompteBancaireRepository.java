package marketplace.ProjetJ2EE_SpringBoot.repository;

import marketplace.ProjetJ2EE_SpringBoot.model.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Integer> {
    List<CompteBancaire> findAllByClient_Id(int clientId);
}
