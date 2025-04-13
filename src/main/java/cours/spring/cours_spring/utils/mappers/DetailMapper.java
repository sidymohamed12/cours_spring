package cours.spring.cours_spring.utils.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.data.entities.Detail;
import cours.spring.cours_spring.web.dto.request.DetailCreateRequest;
import cours.spring.cours_spring.web.dto.response.detail.DetailAllResponse;

@Mapper(componentModel = "spring")
public interface DetailMapper {
    DetailMapper INSTANCE = Mappers.getMapper(DetailMapper.class);

    @Mapping(source = "articleId", target = "article.id")
    @Mapping(source = "commandeId", target = "commande.id")
    Detail toEntity(DetailCreateRequest request);

    @AfterMapping
    default void afterToEntity(@MappingTarget Detail detail, DetailCreateRequest request) {
        if (request.getArticleId() != null) {
            Article article = new Article();
            article.setId(request.getArticleId());
            detail.setArticle(article);
        }
    }

    @Mapping(source = "commande.id", target = "commandeId")
    DetailAllResponse toDetailAllResponse(Detail detail);
}
