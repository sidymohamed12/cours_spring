package cours.spring.cours_spring.utils.mappers;

import cours.spring.cours_spring.data.entities.Categorie;
import cours.spring.cours_spring.web.dto.request.CategoryCreateRequest;
import cours.spring.cours_spring.web.dto.response.category.CategorySimpleResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-10T16:52:31+0000",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Categorie toEntity(CategoryCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Categorie categorie = new Categorie();

        categorie.setCode( request.getCode() );
        categorie.setName( request.getName() );

        return categorie;
    }

    @Override
    public CategorySimpleResponse toSimpleResponse(Categorie categorie) {
        if ( categorie == null ) {
            return null;
        }

        CategorySimpleResponse categorySimpleResponse = new CategorySimpleResponse();

        categorySimpleResponse.setCode( categorie.getCode() );
        categorySimpleResponse.setId( categorie.getId() );
        categorySimpleResponse.setName( categorie.getName() );

        return categorySimpleResponse;
    }
}
