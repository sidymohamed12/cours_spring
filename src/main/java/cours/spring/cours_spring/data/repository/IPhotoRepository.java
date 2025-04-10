package cours.spring.cours_spring.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.data.entities.Photo;

public interface IPhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByArticle(Article article); // Récupérer toutes les photos d'un article donné
}
