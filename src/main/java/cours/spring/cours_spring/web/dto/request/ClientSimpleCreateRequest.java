package cours.spring.cours_spring.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto request de Client")
public class ClientSimpleCreateRequest {

    @Schema(description = "le nom du client est obligatoire")
    @NotBlank(message = "le nom est obligatoire !")
    private String name;

    @Schema(description = "le numero du client est obligatoire")
    @NotBlank(message = "le numero de telephone est obligatoire !")
    @Pattern(regexp = "^[0-9]{9}", message = "le numero est invalide")
    private String telephone;

}
