package cours.spring.cours_spring.web.dto.response.article;

import java.util.List;

import cours.spring.cours_spring.web.dto.response.category.CategorySimpleResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto response de Article avec sa categorie")
public class ArticleOneResponse {
    @Schema(description = "l'id est unique")
    private Integer id;
    private String libelle;
    private String code;
    private Integer qteStock;
    private Double prix;
    private Double newPrix;
    private Integer note;
    private String image;
    private Boolean isdispo;
    private Boolean promo;
    private CategorySimpleResponse category;
    private List<ProduitCatalogue> produitCategory;

}
