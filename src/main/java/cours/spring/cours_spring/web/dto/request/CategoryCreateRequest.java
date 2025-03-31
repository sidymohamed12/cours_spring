package cours.spring.cours_spring.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto request de Categorie")
public class CategoryCreateRequest {

    @Schema(description = "le code de la categorie est obligatoire")
    @NotBlank(message = "le code est obligatoire !")
    private String code;

    @Schema(description = "le nom de la categorie est obligatoire")
    @NotBlank(message = "le nom est obligatoire !")
    private String name;

}
