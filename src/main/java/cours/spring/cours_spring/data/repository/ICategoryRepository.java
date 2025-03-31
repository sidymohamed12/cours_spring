package cours.spring.cours_spring.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import cours.spring.cours_spring.data.entities.Categorie;

public interface ICategoryRepository extends JpaRepository<Categorie, Integer> {
    Page<Categorie> findAll(Pageable pageable);

    boolean existsByName(String name);
}
