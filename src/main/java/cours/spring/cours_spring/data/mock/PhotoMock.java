package cours.spring.cours_spring.data.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cours.spring.cours_spring.data.entities.Article;
import cours.spring.cours_spring.data.entities.Photo;
import cours.spring.cours_spring.data.repository.IArticleRepository;
import cours.spring.cours_spring.data.repository.IPhotoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
// @Component
public class PhotoMock implements CommandLineRunner {
    private final IPhotoRepository photoRepository;
    private final IArticleRepository articleRepository;

    @Override
    public void run(String... args) throws Exception {
        var articles = articleRepository.findAll();
        List<Photo> photos = new ArrayList<>();

        for (Article article : articles) {
            for (int i = 1; i < 6; i++) {
                Photo photo = new Photo();
                photo.setImagePath("img/" + article.getCategorie().getName() + "/" + article.getLibelle() + "s/"
                        + article.getLibelle() + i + ".jpg");
                photo.setArticle(article);
                photos.add(photo);
            }
        }
        photoRepository.saveAllAndFlush(photos);
    }

}
