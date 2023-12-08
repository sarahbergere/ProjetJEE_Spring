package marketplace.ProjetJ2EE_SpringBoot.repository;

import marketplace.ProjetJ2EE_SpringBoot.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {

    @Query(value = "SELECT * FROM Produit ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Produit> findRandomProducts(@Param("limit") int limit);

    @Modifying
    @Query(value="UPDATE Produit SET nom = :nom, prix= :price, stock= :stock, image= :imageUrl, description= :description WHERE produit = :id", nativeQuery = true)
    void update(@Param("id") int id,@Param("nom") String nom,@Param("price") double price,@Param("description") String description,@Param("stock") int stock,@Param("imageUrl") String imageUrl);
}
