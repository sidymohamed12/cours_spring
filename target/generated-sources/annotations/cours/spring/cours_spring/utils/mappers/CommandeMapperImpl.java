package cours.spring.cours_spring.utils.mappers;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.data.entities.Client;
import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.data.entities.Detail;
import cours.spring.cours_spring.data.entities.Photo;
import cours.spring.cours_spring.web.dto.request.CommandeCreateRequest;
import cours.spring.cours_spring.web.dto.request.DetailCreateRequest;
import cours.spring.cours_spring.web.dto.response.article.ArticleAllResponse;
import cours.spring.cours_spring.web.dto.response.client.ClientSimpleResponse;
import cours.spring.cours_spring.web.dto.response.commande.CommandeOneResponse;
import cours.spring.cours_spring.web.dto.response.commande.CommandeSimpleResponse;
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
public class CommandeMapperImpl implements CommandeMapper {

    @Override
    public Commande toEntity(CommandeCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Commande commande = new Commande();

        commande.setClient( commandeCreateRequestToClient( request ) );
        commande.setDate( request.getDate() );
        commande.setDetails( detailCreateRequestListToDetailList( request.getDetails() ) );
        commande.setMontant( request.getMontant() );

        return commande;
    }

    @Override
    public CommandeSimpleResponse toSimpleResponse(Commande commande) {
        if ( commande == null ) {
            return null;
        }

        CommandeSimpleResponse commandeSimpleResponse = new CommandeSimpleResponse();

        commandeSimpleResponse.setClient( mapClientName( commande.getClient() ) );
        commandeSimpleResponse.setDate( commande.getDate() );
        commandeSimpleResponse.setId( commande.getId() );
        commandeSimpleResponse.setMontant( commande.getMontant() );

        return commandeSimpleResponse;
    }

    @Override
    public CommandeOneResponse toOneResponse(Commande commande) {
        if ( commande == null ) {
            return null;
        }

        CommandeOneResponse commandeOneResponse = new CommandeOneResponse();

        commandeOneResponse.setClient( clientToClientSimpleResponse( commande.getClient() ) );
        commandeOneResponse.setDate( commande.getDate() );
        commandeOneResponse.setDetails( detailListToDetailAllResponseList( commande.getDetails() ) );
        commandeOneResponse.setId( commande.getId() );
        commandeOneResponse.setMontant( commande.getMontant() );

        return commandeOneResponse;
    }

    protected Client commandeCreateRequestToClient(CommandeCreateRequest commandeCreateRequest) {
        if ( commandeCreateRequest == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( commandeCreateRequest.getClientId() );

        return client;
    }

    protected Detail detailCreateRequestToDetail(DetailCreateRequest detailCreateRequest) {
        if ( detailCreateRequest == null ) {
            return null;
        }

        Detail detail = new Detail();

        detail.setPrixVente( detailCreateRequest.getPrixVente() );
        detail.setQteVendu( detailCreateRequest.getQteVendu() );

        return detail;
    }

    protected List<Detail> detailCreateRequestListToDetailList(List<DetailCreateRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<Detail> list1 = new ArrayList<Detail>( list.size() );
        for ( DetailCreateRequest detailCreateRequest : list ) {
            list1.add( detailCreateRequestToDetail( detailCreateRequest ) );
        }

        return list1;
    }

    protected ClientSimpleResponse clientToClientSimpleResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientSimpleResponse clientSimpleResponse = new ClientSimpleResponse();

        clientSimpleResponse.setId( client.getId() );
        clientSimpleResponse.setName( client.getName() );
        clientSimpleResponse.setTelephone( client.getTelephone() );

        return clientSimpleResponse;
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

    protected DetailAllResponse detailToDetailAllResponse(Detail detail) {
        if ( detail == null ) {
            return null;
        }

        DetailAllResponse detailAllResponse = new DetailAllResponse();

        detailAllResponse.setArticle( articleToArticleAllResponse( detail.getArticle() ) );
        detailAllResponse.setId( detail.getId() );
        detailAllResponse.setPrixVente( detail.getPrixVente() );
        detailAllResponse.setQteVendu( detail.getQteVendu() );

        return detailAllResponse;
    }

    protected List<DetailAllResponse> detailListToDetailAllResponseList(List<Detail> list) {
        if ( list == null ) {
            return null;
        }

        List<DetailAllResponse> list1 = new ArrayList<DetailAllResponse>( list.size() );
        for ( Detail detail : list ) {
            list1.add( detailToDetailAllResponse( detail ) );
        }

        return list1;
    }
}
