package cours.spring.cours_spring.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailCreateRequest {
    private Integer qteVendu;
    private Double prixVente;
    private Integer commandeId;
    private Integer articleId;
}