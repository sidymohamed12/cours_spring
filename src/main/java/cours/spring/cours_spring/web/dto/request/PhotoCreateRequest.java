package cours.spring.cours_spring.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ceci est le dto request de Photo")
public class PhotoCreateRequest {
    private String imagePath;

    private Integer articleId;
}
