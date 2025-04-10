package cours.spring.cours_spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cours.spring.cours_spring.data.entities.Categorie;

public interface ICategoryRepository extends JpaRepository<Categorie, Integer> {
    // Removed redundant findAll(Pageable pageable) method as it is already
    // inherited

    boolean existsByName(String name);
}
