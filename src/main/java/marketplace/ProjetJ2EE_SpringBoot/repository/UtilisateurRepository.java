package marketplace.ProjetJ2EE_SpringBoot.repository;

import marketplace.ProjetJ2EE_SpringBoot.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query("Select u FROM Utilisateur u WHERE u.pseudo = :username")
    Utilisateur findByUsername(@Param("username") String username);
}
