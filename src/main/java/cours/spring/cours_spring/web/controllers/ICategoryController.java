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
import cours.spring.cours_spring.data.entities.Categorie;
import cours.spring.cours_spring.web.dto.request.CategoryCreateRequest;
import cours.spring.cours_spring.web.dto.response.article.ArticleAllResponse;
import cours.spring.cours_spring.web.dto.response.category.CategorySimpleResponse;

@RequestMapping("/categorie")
public interface ICategoryController extends Icontroller<CategoryCreateRequest> {

        @PutMapping("/{id}")
        ResponseEntity<CategorySimpleResponse> updateCategorie(@PathVariable Integer id,
                        @RequestBody Categorie categorie);

        @DeleteMapping("/{id}")
        ResponseEntity<Boolean> deleteCategorie(@PathVariable Integer id);

        @GetMapping("/{id}/articles")
        ResponseEntity<List<ArticleAllResponse>> getAllArticle(@PathVariable(name = "id") Integer categoryId);

}
