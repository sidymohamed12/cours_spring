package cours.spring.cours_spring.utils.mappers;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.data.entities.Categorie;
import cours.spring.cours_spring.web.dto.request.ArticleCreateRequest;
import cours.spring.cours_spring.web.dto.response.article.ArticleAllResponse;
import cours.spring.cours_spring.web.dto.response.article.ArticleOneResponse;
import cours.spring.cours_spring.web.dto.response.article.ProduitCatalogue;
import cours.spring.cours_spring.web.dto.response.category.CategorySimpleResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-02T13:59:58+0000",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public Article toEntity(ArticleCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Article article = new Article();

        article.setCategorie( articleCreateRequestToCategorie( request ) );
        article.setDescription( request.getDescription() );
        article.setImage( request.getImage() );
        article.setIsdispo( request.getIsdispo() );
        article.setLibelle( request.getLibelle() );
        article.setNewPrix( request.getNewPrix() );
        article.setNote( request.getNote() );
        article.setPrix( request.getPrix() );
        article.setPromo( request.getPromo() );
        article.setQteStock( request.getQteStock() );

        return article;
    }

    @Override
    public ArticleOneResponse toOneResponse(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleOneResponse articleOneResponse = new ArticleOneResponse();

        articleOneResponse.setCategory( categorieToCategorySimpleResponse( article.getCategorie() ) );
        articleOneResponse.setCode( article.getCode() );
        articleOneResponse.setDescription( article.getDescription() );
        articleOneResponse.setId( article.getId() );
        articleOneResponse.setImage( article.getImage() );
        articleOneResponse.setIsdispo( article.getIsdispo() );
        articleOneResponse.setLibelle( article.getLibelle() );
        articleOneResponse.setNewPrix( article.getNewPrix() );
        articleOneResponse.setNote( article.getNote() );
        articleOneResponse.setPrix( article.getPrix() );
        articleOneResponse.setPromo( article.getPromo() );
        articleOneResponse.setQteStock( article.getQteStock() );

        return articleOneResponse;
    }

    @Override
    public ArticleAllResponse toAllResponse(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleAllResponse articleAllResponse = new ArticleAllResponse();

        articleAllResponse.setCategory( articleCategorieName( article ) );
        articleAllResponse.setCode( article.getCode() );
        articleAllResponse.setDescription( article.getDescription() );
        articleAllResponse.setId( article.getId() );
        articleAllResponse.setLibelle( article.getLibelle() );
        articleAllResponse.setPrix( article.getPrix() );
        articleAllResponse.setQteStock( article.getQteStock() );

        return articleAllResponse;
    }

    @Override
    public ProduitCatalogue toCatalogue(Article article) {
        if ( article == null ) {
            return null;
        }

        ProduitCatalogue produitCatalogue = new ProduitCatalogue();

        produitCatalogue.setDescription( article.getDescription() );
        produitCatalogue.setId( article.getId() );
        produitCatalogue.setImage( article.getImage() );
        produitCatalogue.setIsdispo( article.getIsdispo() );
        produitCatalogue.setLibelle( article.getLibelle() );
        produitCatalogue.setNewPrix( article.getNewPrix() );
        produitCatalogue.setNote( article.getNote() );
        produitCatalogue.setPrix( article.getPrix() );
        produitCatalogue.setPromo( article.getPromo() );
        produitCatalogue.setQteStock( article.getQteStock() );

        return produitCatalogue;
    }

    protected Categorie articleCreateRequestToCategorie(ArticleCreateRequest articleCreateRequest) {
        if ( articleCreateRequest == null ) {
            return null;
        }

        Categorie categorie = new Categorie();

        categorie.setId( articleCreateRequest.getCategoryId() );

        return categorie;
    }

    protected CategorySimpleResponse categorieToCategorySimpleResponse(Categorie categorie) {
        if ( categorie == null ) {
            return null;
        }

        CategorySimpleResponse categorySimpleResponse = new CategorySimpleResponse();

        categorySimpleResponse.setCode( categorie.getCode() );
        categorySimpleResponse.setId( categorie.getId() );
        categorySimpleResponse.setName( categorie.getName() );

        return categorySimpleResponse;
    }

    private String articleCategorieName(Article article) {
        Categorie categorie = article.getCategorie();
        if ( categorie == null ) {
            return null;
        }
        return categorie.getName();
    }
}
