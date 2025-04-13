package cours.spring.cours_spring.web.dto.response.detail;

import cours.spring.cours_spring.web.dto.response.article.ArticleAllResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailAllResponse {
    private Integer id;
    private Integer qteVendu;
    private Double prixVente;
    private Integer commandeId;
    private ArticleAllResponse article;
}
