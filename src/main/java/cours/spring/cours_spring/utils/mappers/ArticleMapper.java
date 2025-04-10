package cours.spring.cours_spring.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.web.dto.request.ArticleCreateRequest;
import cours.spring.cours_spring.web.dto.response.article.ArticleAllResponse;
import cours.spring.cours_spring.web.dto.response.article.ArticleOneResponse;
import cours.spring.cours_spring.web.dto.response.article.ProduitCatalogue;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(source = "categoryId", target = "categorie.id")
    Article toEntity(ArticleCreateRequest request);

    @Mapping(source = "categorie", target = "category")
    ArticleOneResponse toOneResponse(Article article);

    @Mapping(source = "categorie.name", target = "category")
    ArticleAllResponse toAllResponse(Article article);

    @Mapping(source = "categorie.name", target = "category")
    ProduitCatalogue toCatalogue(Article article);

}
