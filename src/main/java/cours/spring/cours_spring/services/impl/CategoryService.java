package cours.spring.cours_spring.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cours.spring.cours_spring.data.entities.Categorie;
import cours.spring.cours_spring.data.repository.ICategoryRepository;
import cours.spring.cours_spring.services.ICategoryService;
import cours.spring.cours_spring.utils.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;

    @Override
    public Categorie create(Categorie value) {
        return categoryRepository.save(value);
    }

    @Override
    public List<Categorie> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Categorie> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Categorie update(Integer id, Categorie value) {
        Categorie data = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (data != null) {
            data.setCode(value.getCode());
            data.setName(value.getName());
            categoryRepository.save(data);
        }
        return data;
    }

    @Override
    public Categorie getById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
    }

    @Override
    public Boolean delete(Integer id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (category != null) {
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }

}
