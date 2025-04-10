package cours.spring.cours_spring.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto request de Article")
public class ArticleCreateRequest {

    @Schema(description = "le libelle est obligatoire")
    @NotBlank(message = "le libelle est obligatoire !")
    private String libelle;

    @Schema(description = "la quantité est obligatoire")
    private Integer qteStock;

    @Schema(description = "le prix est obligatoire")
    private Double prix;

    @Schema(description = "l'id de la categorie est obligatoire")
    @NotNull
    private Integer categoryId;

    private Double newPrix;
    private Integer note;
    private Boolean isdispo;
    private Boolean promo;
    private String description;
}
