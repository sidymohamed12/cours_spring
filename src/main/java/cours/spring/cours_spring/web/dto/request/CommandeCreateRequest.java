package cours.spring.cours_spring.web.dto.request;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto request de Commande")
public class CommandeCreateRequest {
    private Double montant;
    private LocalDateTime date = LocalDateTime.now();
    private Integer clientId;
}
