package cours.spring.cours_spring.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String libelle;
    private String code;
    @Column(nullable = false)
    private Integer qteStock;
    @Column(nullable = false)
    private Double prix;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    // @Column(nullable = false)
    private String image;

    private Double newPrix;
    private Integer note;
    private Boolean isdispo;
    private Boolean promo;
    @ManyToOne
    private Categorie categorie;
    @OneToMany(mappedBy = "article")
    private List<Detail> details = new ArrayList<>();

}
