package cours.spring.cours_spring.web.dto.request;

import java.util.List;

import cours.spring.cours_spring.utils.validator.ListNotEmpty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto request de Client avec liste de Commandes")
public class ClientAndCommandeCreateRequest {

    @Schema(description = "le nom du client est obligatoire")
    @NotBlank(message = "le nom est obligatoire !")
    private String name;

    @Schema(description = "le telephone du client est obligatoire")
    @NotBlank(message = "le telephone est obligatoire !")
    private String telephone;

    @Schema(description = "la liste de commandes du client ne doit pas être vide")
    @NotNull
    @ListNotEmpty
    private List<CommandeCreateRequest> commandes;
}
