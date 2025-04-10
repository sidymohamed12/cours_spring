package cours.spring.cours_spring.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import cours.spring.cours_spring.data.entities.Photo;
import cours.spring.cours_spring.web.dto.request.PhotoCreateRequest;
import cours.spring.cours_spring.web.dto.response.photo.PhotoAllResponse;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    PhotoMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(PhotoMapper.class);

    @Mapping(source = "articleId", target = "article.id")
    Photo toEntity(PhotoCreateRequest photoDTO);

    @Mapping(source = "article.libelle", target = "articleLibelle")
    PhotoAllResponse toPhotoAllResponse(Photo photo);

}
