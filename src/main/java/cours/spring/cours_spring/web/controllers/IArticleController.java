package cours.spring.cours_spring.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import cours.spring.cours_spring.config.Icontroller;
import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.web.dto.request.ArticleCreateRequest;
import cours.spring.cours_spring.web.dto.response.article.ArticleOneResponse;
import cours.spring.cours_spring.web.dto.response.article.ProduitCatalogue;

@RequestMapping("/article")
public interface IArticleController extends Icontroller<ArticleCreateRequest> {

        @PutMapping("/{id}")
        ResponseEntity<ArticleOneResponse> updateArticle(@PathVariable Integer id, @RequestBody Article article);

        @DeleteMapping("/{id}")
        ResponseEntity<Boolean> deleteArticle(@PathVariable Integer id);

        @GetMapping("/catalogue")
        ResponseEntity<List<ProduitCatalogue>> getCatalogue();
}
