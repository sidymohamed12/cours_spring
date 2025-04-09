package cours.spring.cours_spring.web.dto.response.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto response de Article avec sa categorie en string")
public class ArticleAllResponse {
    @Schema(description = "l'id est unique")
    private Integer id;
    private String libelle;
    private String code;
    private Integer qteStock;
    private Double prix;
    private String category;
    private String description;

}
