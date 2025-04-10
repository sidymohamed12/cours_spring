package cours.spring.cours_spring.data.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.data.entities.Categorie;
import cours.spring.cours_spring.data.repository.IArticleRepository;
import cours.spring.cours_spring.data.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

// @Component
// @Order(3)
@RequiredArgsConstructor
public class ArticleMock implements CommandLineRunner {

    private final IArticleRepository articleRepository;
    private final ICategoryRepository categoryRepository;
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        List<Categorie> categories = categoryRepository.findAll();
        List<Article> articles = new ArrayList<>();

        for (Categorie categorie : categories) {
            for (int i = 1; i < 5; i++) {
                Article article = new Article();
                article.setCode(categorie.getName() + "_codeArt_" + i);
                article.setLibelle(categorie.getId() + "_article_" + i);
                article.setPrix(10000.0 * i);
                article.setPromo(false);
                article.setNewPrix(0.0);

                if (i % 2 == 0) {
                    article.setPromo(true);
                    article.setNewPrix(3000.0 * i);
                }

                article.setNote(random.nextInt(5) + 1);
                article.setQteStock(i * 2);
                article.setCategorie(categorie);
                articles.add(article);
            }
        }
        articleRepository.saveAllAndFlush(articles);

    }

}
