package cours.spring.cours_spring.web.dto.response.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto response de Client")
public class ClientSimpleResponse {
    @Schema(description = "l'id est unique")
    private Integer id;
    private String name;
    private String telephone;
}
