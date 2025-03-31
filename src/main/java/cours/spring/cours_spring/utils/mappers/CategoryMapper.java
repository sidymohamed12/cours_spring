package cours.spring.cours_spring.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cours.spring.cours_spring.data.entities.Categorie;
import cours.spring.cours_spring.web.dto.request.CategoryCreateRequest;
import cours.spring.cours_spring.web.dto.response.category.CategorySimpleResponse;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Categorie toEntity(CategoryCreateRequest request);

    CategorySimpleResponse toSimpleResponse(Categorie categorie);
}
