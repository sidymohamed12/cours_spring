package cours.spring.cours_spring.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cours.spring.cours_spring.config.IService;
import cours.spring.cours_spring.data.entities.Article;

public interface IArticleService extends IService<Article> {
    List<Article> getAllArticleByCategorie(Integer categorieId);

    Page<Article> getAll(Pageable pageable);
}
