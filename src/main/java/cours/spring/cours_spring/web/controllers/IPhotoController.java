package cours.spring.cours_spring.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cours.spring.cours_spring.config.Icontroller;
import cours.spring.cours_spring.web.dto.request.PhotoCreateRequest;
import cours.spring.cours_spring.web.dto.response.photo.PhotoAllResponse;

@RequestMapping("/photo")
public interface IPhotoController extends Icontroller<PhotoCreateRequest> {

    @GetMapping("/article/{articleId}")
    ResponseEntity<List<PhotoAllResponse>> getPhotosByArticleId(@PathVariable Integer articleId);

}
