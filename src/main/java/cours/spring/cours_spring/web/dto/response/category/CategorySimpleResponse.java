package cours.spring.cours_spring.web.dto.response.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto response de Category")
public class CategorySimpleResponse {
    @Schema(description = "l'id est unique")
    private Integer id;
    private String code;
    private String name;

}
