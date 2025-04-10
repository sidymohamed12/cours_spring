package cours.spring.cours_spring.web.dto.response.article;

import java.util.List;

import cours.spring.cours_spring.web.dto.response.photo.PhotoAllResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitCatalogue {
    private Integer id;
    private String libelle;
    private Double prix;
    private Double newPrix;
    private Integer qteStock;
    private Integer note;
    private String image;
    private Boolean isdispo;
    private Boolean promo;
    private String description;
    private List<PhotoAllResponse> photos;
}
