package cours.spring.cours_spring.web.controllers.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.services.IArticleService;
import cours.spring.cours_spring.services.ICategoryService;
import cours.spring.cours_spring.services.IPhotoService;
import cours.spring.cours_spring.utils.mappers.ArticleMapper;
import cours.spring.cours_spring.utils.mappers.PhotoMapper;
import cours.spring.cours_spring.web.controllers.IArticleController;
import cours.spring.cours_spring.web.dto.RestResponse;
import cours.spring.cours_spring.web.dto.request.ArticleCreateRequest;
import cours.spring.cours_spring.web.dto.response.article.ArticleAllResponse;
import cours.spring.cours_spring.web.dto.response.article.ArticleOneResponse;
import cours.spring.cours_spring.web.dto.response.article.ProduitCatalogue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Articles", description = "gestion des articles")
public class ArticleController implements IArticleController {

    private final IArticleService articleService;
    private final IPhotoService photoService;
    private final PhotoMapper photoMapper;
    private final ArticleMapper articleMapper;

    @Override
    @Operation(summary = "récupérer tous les articles", description = "retourne une pagination d'articles ainsi le nom de la catégorie")
    public ResponseEntity<Map<String, Object>> getAll(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var articles = articleService.getAll(pageable);
        var articlesResponse = articles.map(articleMapper::toAllResponse);
        var processedArticles = articlesResponse.getContent()
                .stream()
                .map(articleAllResponse -> {
                    var photosArticle = photoService.getAllPhotosByArticle(articleAllResponse.getId());
                    var photosResponse = photosArticle.stream()
                            .map(photoMapper::toPhotoAllResponse)
                            .toList();
                    articleAllResponse.setPhotos(photosResponse);
                    return articleAllResponse;
                })
                .toList();
        var totalPages = articlesResponse.getTotalPages();

        return new ResponseEntity<>(RestResponse.responsePaginate(
                HttpStatus.OK, processedArticles, "articlesResponse", new int[totalPages],
                articlesResponse.getNumber(), totalPages, articlesResponse.getTotalElements(),
                articlesResponse.isFirst(), articlesResponse.isLast()), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "récupérer un article par son id", description = "retourne un article ainsi que sa categorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "l'article a été trouvé"),
            @ApiResponse(responseCode = "404", description = "l'article non trouvé")
    })
    public ResponseEntity<Map<String, Object>> getOne(Integer id) {
        var article = articleService.getById(id);
        if (article == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var response = articleMapper.toOneResponse(article);
        return new ResponseEntity<>(RestResponse.response(
                HttpStatus.CREATED,
                response, "ArticleOneResponse"), HttpStatus.OK);
    }

    @Operation(summary = "récupérer un article par son id", description = "retourne un article ainsi que sa categorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "l'article a été trouvé"),
            @ApiResponse(responseCode = "404", description = "l'article non trouvé")
    })
    @GetMapping("/catalogue/{id}")
    public ResponseEntity<ArticleOneResponse> getOne2(@PathVariable Integer id) {
        var article = articleService.getById(id);
        if (article == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var response = articleMapper.toOneResponse(article);
        var photosArticle = photoService.getAllPhotosByArticle(article.getId());
        var photosResponse = photosArticle.stream()
                .map(photoMapper::toPhotoAllResponse)
                .toList();

        // --------------------

        var produitCategory = articleService.getAllArticleByCategorie(response.getCategory().getId());
        var produitCategoryResponse = produitCategory
                .stream()
                .map(articleMapper::toCatalogue)
                .toList();

        response.setProduitCategory(produitCategoryResponse);
        response.setPhotos(photosResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> create(ArticleCreateRequest article,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            var errors = RestResponse.extractFieldErrors(bindingResult);
            return new ResponseEntity<>(RestResponse.response(
                    HttpStatus.BAD_REQUEST, errors, "Map<String, String>"), HttpStatus.BAD_REQUEST);
        } else {
            var data = articleService.create(articleMapper.toEntity(article));
            var response = articleMapper.toOneResponse(data);
            return new ResponseEntity<>(RestResponse.response(
                    HttpStatus.CREATED,
                    response, "ArticleOneResponse"), HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<ArticleOneResponse> updateArticle(Integer id, Article article) {
        var data = articleService.update(id, article);
        var response = articleMapper.toOneResponse(data);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Boolean> deleteArticle(Integer id) {
        var article = articleService.delete(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProduitCatalogue>> getCatalogue() {
        var articles = articleService.getAll();
        var articlesResponse = articles
                .stream()
                .map(articleMapper::toCatalogue)
                .toList();
        var response = articlesResponse.stream()
                .map(article -> {
                    var photosArticle = photoService.getAllPhotosByArticle(article.getId());
                    var photosResponse = photosArticle.stream()
                            .map(photoMapper::toPhotoAllResponse)
                            .toList();
                    article.setPhotos(photosResponse);
                    return article;
                })
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
