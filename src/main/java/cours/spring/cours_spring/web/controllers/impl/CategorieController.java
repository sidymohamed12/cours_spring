package cours.spring.cours_spring.web.controllers.impl;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import cours.spring.cours_spring.data.entities.Categorie;
import cours.spring.cours_spring.services.IArticleService;
import cours.spring.cours_spring.services.ICategoryService;
import cours.spring.cours_spring.utils.mappers.ArticleMapper;
import cours.spring.cours_spring.utils.mappers.CategoryMapper;
import cours.spring.cours_spring.web.controllers.ICategoryController;
import cours.spring.cours_spring.web.dto.RestResponse;
import cours.spring.cours_spring.web.dto.request.CategoryCreateRequest;
import cours.spring.cours_spring.web.dto.response.article.ArticleAllResponse;
import cours.spring.cours_spring.web.dto.response.category.CategorySimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Categories", description = "gestion des categories")
public class CategorieController implements ICategoryController {

    private final ICategoryService categoryService;
    private final IArticleService articleService;
    private final CategoryMapper categoryMapper;
    private final ArticleMapper articleMapper;

    @Override
    @Operation(summary = "récupérer toutes les catégories", description = "retourne une pagination de categories")
    public ResponseEntity<Map<String, Object>> getAll(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var categories = categoryService.getAll(pageable);
        var categoryResponse = categories.map(categoryMapper::toSimpleResponse);
        var totalPages = categoryResponse.getTotalPages();

        return new ResponseEntity<>(RestResponse.responsePaginate(
                HttpStatus.OK, categoryResponse.getContent(), "CategorieSimpleResponse",
                new int[totalPages], categoryResponse.getNumber(), totalPages,
                categoryResponse.getTotalElements(), categoryResponse.isFirst(), categoryResponse.isLast()),
                HttpStatus.OK);
    }

    @Override
    @Operation(summary = "récupérer une catégorie par son id", description = "retourne une categorie")
    public ResponseEntity<Map<String, Object>> getOne(Integer id) {
        var category = categoryService.getById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var response = categoryMapper.toSimpleResponse(category);
        return new ResponseEntity<>(RestResponse.response(
                HttpStatus.OK,
                response, "CategorySimpleResponse"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> create(CategoryCreateRequest categorie,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errors = RestResponse.extractFieldErrors(bindingResult);
            return new ResponseEntity<>(RestResponse.response(
                    HttpStatus.BAD_REQUEST, errors, "Map<String, String>"), HttpStatus.BAD_REQUEST);
        } else {
            var categorieEntity = categoryMapper.toEntity(categorie);
            var category = categoryService.create(categorieEntity);
            var response = categoryMapper.toSimpleResponse(category);
            return new ResponseEntity<>(RestResponse.response(
                    HttpStatus.CREATED,
                    response, "CategorySimpleResponse"), HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<CategorySimpleResponse> updateCategorie(Integer id, Categorie categorie) {
        throw new UnsupportedOperationException("Unimplemented method 'updateCategorie'");
    }

    @Override
    public ResponseEntity<Boolean> deleteCategorie(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategorie'");
    }

    @Override
    public ResponseEntity<List<ArticleAllResponse>> getAllArticle(Integer categoryId) {
        var articles = articleService.getAllArticleByCategorie(categoryId);
        var articlesResponse = articles.stream().map(articleMapper::toAllResponse).toList();
        return new ResponseEntity<>(articlesResponse, HttpStatus.OK);
    }

}
