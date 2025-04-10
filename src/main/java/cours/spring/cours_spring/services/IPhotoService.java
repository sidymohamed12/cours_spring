package cours.spring.cours_spring.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import cours.spring.cours_spring.config.IService;
import cours.spring.cours_spring.data.entities.Photo;

public interface IPhotoService extends IService<Photo> {
    Page<Photo> getAll(Pageable pageable); // Pagination des photos

    List<Photo> getAllPhotosByArticle(Integer articleId);
}
