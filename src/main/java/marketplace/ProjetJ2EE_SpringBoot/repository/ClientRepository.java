package marketplace.ProjetJ2EE_SpringBoot.repository;

import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import marketplace.ProjetJ2EE_SpringBoot.model.Commande;
import marketplace.ProjetJ2EE_SpringBoot.model.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT c FROM Client c WHERE c.idUtilisateur = (SELECT u.id FROM Utilisateur u WHERE u.pseudo = :username)")
    Optional<Client> findByUsername(@Param("username") String username);

    @Query("SELECT u.motDePasse FROM Utilisateur u JOIN Client c ON u.id = c.idUtilisateur WHERE c.idUtilisateur = :clientId")
    String getPasswordById(@Param("clientId") int clientId);

    List<Commande> findCommandesById(int clientId);

    List<CompteBancaire> findComptesById(int clientId);
}
