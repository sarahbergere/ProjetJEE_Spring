package marketplace.ProjetJ2EE_SpringBoot.repository;

import marketplace.ProjetJ2EE_SpringBoot.model.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Integer> {
    @Query("SELECT cb FROM CompteBancaire cb WHERE cb.client.id = :clientId")
    List<CompteBancaire> findAllByIdClient(@Param("clientId") int clientId);

}
