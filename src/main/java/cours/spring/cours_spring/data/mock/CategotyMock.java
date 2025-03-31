package cours.spring.cours_spring.data.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cours.spring.cours_spring.data.entities.Categorie;
import cours.spring.cours_spring.data.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@Component
@Order(2)
@RequiredArgsConstructor
public class CategotyMock implements CommandLineRunner {

    private final ICategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Categorie> categories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Categorie categorie = new Categorie();
            categorie.setCode("codeCat" + i);
            categorie.setName("categorie" + i);
            categories.add(categorie);
        }
        categoryRepository.saveAllAndFlush(categories);
    }

}
