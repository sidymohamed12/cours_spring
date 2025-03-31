package cours.spring.cours_spring.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cours.spring.cours_spring.config.IService;
import cours.spring.cours_spring.data.entities.Categorie;

public interface ICategoryService extends IService<Categorie> {
    Page<Categorie> getAll(Pageable pageable);
}
