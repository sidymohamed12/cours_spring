package cours.spring.cours_spring.web.controllers.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import cours.spring.cours_spring.services.IArticleService;
import cours.spring.cours_spring.services.IPhotoService;
import cours.spring.cours_spring.utils.mappers.PhotoMapper;
import cours.spring.cours_spring.web.controllers.IPhotoController;
import cours.spring.cours_spring.web.dto.RestResponse;
import cours.spring.cours_spring.web.dto.request.PhotoCreateRequest;
import cours.spring.cours_spring.web.dto.response.photo.PhotoAllResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PhotoController implements IPhotoController {

    private final IPhotoService photoService;
    private final IArticleService articleService;
    private final PhotoMapper photoMapper;

    @Override
    public ResponseEntity<Map<String, Object>> getAll(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var photos = photoService.getAll(pageable);
        var response = photos.map(photoMapper::toPhotoAllResponse);
        var totalPages = response.getTotalPages();
        return new ResponseEntity<>(RestResponse.responsePaginate(
                HttpStatus.OK,
                response.getContent(),
                "PhotoAllResponse",
                new int[totalPages],
                response.getNumber(),
                totalPages,
                response.getTotalElements(),
                response.isFirst(),
                response.isLast()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getOne(Integer id) {
        var photo = photoService.getById(id);
        if (photo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var response = photoMapper.toPhotoAllResponse(photo);
        return new ResponseEntity<>(RestResponse.response(HttpStatus.OK, response, "PhotoAllResponse"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> create(@Valid PhotoCreateRequest request, BindingResult bindingResult) {
        var data = photoService.create(photoMapper.toEntity(request));
        var response = photoMapper.toPhotoAllResponse(data);
        return new ResponseEntity<>(RestResponse.response(HttpStatus.CREATED, response, "PhotoAllResponse"),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<PhotoAllResponse>> getPhotosByArticleId(Integer articleId) {
        var article = articleService.getById(articleId);
        if (article == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var photos = photoService.getAllPhotosByArticle(articleId);
        var response = photos.stream().map(photoMapper::toPhotoAllResponse).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
