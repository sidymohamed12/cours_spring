package cours.spring.cours_spring.services.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import cours.spring.cours_spring.data.entities.Photo;
import cours.spring.cours_spring.data.repository.IArticleRepository;
import cours.spring.cours_spring.data.repository.IPhotoRepository;
import cours.spring.cours_spring.services.IPhotoService;
import cours.spring.cours_spring.utils.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class PhotoService implements IPhotoService {

    private final IPhotoRepository photoRepository;
    private final IArticleRepository articleRepository;

    @Override
    public Photo create(Photo value) {
        return photoRepository.save(value);
    }

    @Override
    public List<Photo> getAll() {
        return photoRepository.findAll();
    }

    @Override
    public Page<Photo> getAll(Pageable pageable) {
        return photoRepository.findAll(pageable);
    }

    @Override
    public Photo update(Integer id, Photo value) {
        Photo data = photoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (data != null) {
            data.setImageFile(value.getImageFile());
            data.setArticle(value.getArticle());
            photoRepository.save(data);
        }
        return data;
    }

    @Override
    public Photo getById(Integer id) {
        return photoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
    }

    @Override
    public Boolean delete(Integer id) {
        var photo = photoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (photo != null) {
            photoRepository.delete(photo);
            return true;
        }
        return false;
    }

    @Override
    public List<Photo> getAllPhotosByArticle(Integer articleId) {
        var article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (article == null)
            return null;
        return photoRepository.findByArticle(article);
    }

}
