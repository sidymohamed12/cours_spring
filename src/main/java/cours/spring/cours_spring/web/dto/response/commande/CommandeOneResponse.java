package cours.spring.cours_spring.web.dto.response.commande;

import java.time.LocalDateTime;
import java.util.List;

import cours.spring.cours_spring.web.dto.response.client.ClientSimpleResponse;
import cours.spring.cours_spring.web.dto.response.detail.DetailAllResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto response de Commande avec son Client")
public class CommandeOneResponse {
    @Schema(description = "l'id est unique")
    private Integer id;
    private Double montant;
    private LocalDateTime date;
    private ClientSimpleResponse client;
    private List<DetailAllResponse> details;
}
