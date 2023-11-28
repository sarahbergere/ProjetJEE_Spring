package marketplace.ProjetJ2EE_SpringBoot.repository;

import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByIdUtilisateur(int idUtilisateur);
}
