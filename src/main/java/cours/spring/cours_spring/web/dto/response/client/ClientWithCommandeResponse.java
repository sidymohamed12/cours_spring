package cours.spring.cours_spring.web.dto.response.client;

import java.util.List;

import cours.spring.cours_spring.web.dto.response.commande.CommandeSimpleResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto response de Client avec sa liste de commande")
public class ClientWithCommandeResponse {
    @Schema(description = "l'id est unique")
    private Integer id;
    private String name;
    private String telephone;
    private List<CommandeSimpleResponse> commandes;
}
