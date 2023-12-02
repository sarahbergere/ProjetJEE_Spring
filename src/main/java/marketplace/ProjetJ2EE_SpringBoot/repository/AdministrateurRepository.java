package marketplace.ProjetJ2EE_SpringBoot.repository;

import marketplace.ProjetJ2EE_SpringBoot.model.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {

    @Query("SELECT a FROM Administrateur a WHERE a.idUtilisateur = (SELECT u.id FROM Utilisateur u WHERE u.pseudo = :username)")
    Optional<Administrateur> findByUsername(@Param("username") String username);
    @Query("SELECT u.motDePasse FROM Utilisateur u JOIN Administrateur a ON u.id = a.idUtilisateur WHERE a.idUtilisateur = :adminId")
    String getPasswordById(@Param("adminId") int adminId);
}
