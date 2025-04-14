package cours.spring.cours_spring.utils.mappers;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.data.entities.Detail;
import cours.spring.cours_spring.data.entities.Photo;
import cours.spring.cours_spring.web.dto.request.DetailCreateRequest;
import cours.spring.cours_spring.web.dto.response.article.ArticleAllResponse;
import cours.spring.cours_spring.web.dto.response.detail.DetailAllResponse;
import cours.spring.cours_spring.web.dto.response.photo.PhotoAllResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-14T19:18:01+0000",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class DetailMapperImpl implements DetailMapper {

    @Override
    public Detail toEntity(DetailCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Detail detail = new Detail();

        detail.setArticle( detailCreateRequestToArticle( request ) );
        detail.setCommande( detailCreateRequestToCommande( request ) );
        detail.setPrixVente( request.getPrixVente() );
        detail.setQteVendu( request.getQteVendu() );

        afterToEntity( detail, request );

        return detail;
    }

    @Override
    public DetailAllResponse toDetailAllResponse(Detail detail) {
        if ( detail == null ) {
            return null;
        }

        DetailAllResponse detailAllResponse = new DetailAllResponse();

        detailAllResponse.setCommandeId( detailCommandeId( detail ) );
        detailAllResponse.setArticle( articleToArticleAllResponse( detail.getArticle() ) );
        detailAllResponse.setId( detail.getId() );
        detailAllResponse.setPrixVente( detail.getPrixVente() );
        detailAllResponse.setQteVendu( detail.getQteVendu() );

        return detailAllResponse;
    }

    protected Article detailCreateRequestToArticle(DetailCreateRequest detailCreateRequest) {
        if ( detailCreateRequest == null ) {
            return null;
        }

        Article article = new Article();

        article.setId( detailCreateRequest.getArticleId() );

        return article;
    }

    protected Commande detailCreateRequestToCommande(DetailCreateRequest detailCreateRequest) {
        if ( detailCreateRequest == null ) {
            return null;
        }

        Commande commande = new Commande();

        commande.setId( detailCreateRequest.getCommandeId() );

        return commande;
    }

    private Integer detailCommandeId(Detail detail) {
        Commande commande = detail.getCommande();
        if ( commande == null ) {
            return null;
        }
        return commande.getId();
    }

    protected PhotoAllResponse photoToPhotoAllResponse(Photo photo) {
        if ( photo == null ) {
            return null;
        }

        PhotoAllResponse photoAllResponse = new PhotoAllResponse();

        photoAllResponse.setId( photo.getId() );
        photoAllResponse.setImagePath( photo.getImagePath() );

        return photoAllResponse;
    }

    protected List<PhotoAllResponse> photoListToPhotoAllResponseList(List<Photo> list) {
        if ( list == null ) {
            return null;
        }

        List<PhotoAllResponse> list1 = new ArrayList<PhotoAllResponse>( list.size() );
        for ( Photo photo : list ) {
            list1.add( photoToPhotoAllResponse( photo ) );
        }

        return list1;
    }

    protected ArticleAllResponse articleToArticleAllResponse(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleAllResponse articleAllResponse = new ArticleAllResponse();

        articleAllResponse.setCode( article.getCode() );
        articleAllResponse.setDescription( article.getDescription() );
        articleAllResponse.setId( article.getId() );
        articleAllResponse.setLibelle( article.getLibelle() );
        articleAllResponse.setPhotos( photoListToPhotoAllResponseList( article.getPhotos() ) );
        articleAllResponse.setPrix( article.getPrix() );
        articleAllResponse.setQteStock( article.getQteStock() );

        return articleAllResponse;
    }
}
