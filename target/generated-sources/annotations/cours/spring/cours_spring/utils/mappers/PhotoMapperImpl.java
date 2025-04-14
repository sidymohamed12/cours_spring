package cours.spring.cours_spring.utils.mappers;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.data.entities.Photo;
import cours.spring.cours_spring.web.dto.request.PhotoCreateRequest;
import cours.spring.cours_spring.web.dto.response.photo.PhotoAllResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-14T19:18:00+0000",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class PhotoMapperImpl implements PhotoMapper {

    @Override
    public Photo toEntity(PhotoCreateRequest photoDTO) {
        if ( photoDTO == null ) {
            return null;
        }

        Photo photo = new Photo();

        photo.setArticle( photoCreateRequestToArticle( photoDTO ) );
        photo.setImagePath( photoDTO.getImagePath() );

        return photo;
    }

    @Override
    public PhotoAllResponse toPhotoAllResponse(Photo photo) {
        if ( photo == null ) {
            return null;
        }

        PhotoAllResponse photoAllResponse = new PhotoAllResponse();

        photoAllResponse.setArticleLibelle( photoArticleLibelle( photo ) );
        photoAllResponse.setId( photo.getId() );
        photoAllResponse.setImagePath( photo.getImagePath() );

        return photoAllResponse;
    }

    protected Article photoCreateRequestToArticle(PhotoCreateRequest photoCreateRequest) {
        if ( photoCreateRequest == null ) {
            return null;
        }

        Article article = new Article();

        article.setId( photoCreateRequest.getArticleId() );

        return article;
    }

    private String photoArticleLibelle(Photo photo) {
        Article article = photo.getArticle();
        if ( article == null ) {
            return null;
        }
        return article.getLibelle();
    }
}
