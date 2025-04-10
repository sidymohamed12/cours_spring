package cours.spring.cours_spring.web.dto.response.photo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoAllResponse {
    private Integer id;
    private String imagePath;
    private String articleLibelle;
}
