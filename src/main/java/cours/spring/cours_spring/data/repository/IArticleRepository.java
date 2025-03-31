package cours.spring.cours_spring.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.data.entities.Categorie;

public interface IArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findArticlesByCategorie(Categorie categorie);

    List<Article> findByCategorie(Categorie categorie);

    Page<Article> findAll(Pageable pageable);

    boolean existsByLibelle(String libelle);

}
