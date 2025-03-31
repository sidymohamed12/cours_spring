package cours.spring.cours_spring.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.data.repository.IArticleRepository;
import cours.spring.cours_spring.data.repository.ICategoryRepository;
import cours.spring.cours_spring.services.IArticleService;
import cours.spring.cours_spring.utils.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ArticleService implements IArticleService {

    private final IArticleRepository articleRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    public Article create(Article value) {
        var categorie = categoryRepository.findById(value.getCategorie().getId())
                .orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (categorie == null)
            return null;
        value.setCategorie(categorie);
        return articleRepository.save(value);
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Page<Article> getAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public Article update(Integer id, Article value) {
        Article data = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        var categorie = categoryRepository.findById(value.getCategorie().getId())
                .orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));

        if (data != null && categorie != null) {
            data.setCode(value.getCode());
            data.setLibelle(value.getLibelle());
            data.setPrix(value.getPrix());
            data.setQteStock(value.getQteStock());
            data.setCategorie(categorie);
            articleRepository.save(data);
        }
        return data;
    }

    @Override
    public Article getById(Integer id) {
        return articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
    }

    @Override
    public Boolean delete(Integer id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (article != null) {
            articleRepository.delete(article);
            return true;
        }
        return false;
    }

    @Override
    public List<Article> getAllArticleByCategorie(Integer categorieId) {
        var categorie = categoryRepository.findById(categorieId)
                .orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (categorie == null)
            return null;
        return articleRepository.findByCategorie(categorie);
    }

}
