package cours.spring.cours_spring.config;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

// <T,R>
public interface Icontroller<R> {

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size);

    @GetMapping("/{id}")
    ResponseEntity<Map<String, Object>> getOne(
            @Parameter(description = "l'id", required = true) @PathVariable Integer id);

    @PostMapping("")
    ResponseEntity<Map<String, Object>> create(@Valid @RequestBody R request, BindingResult bindingResult);

    // @PutMapping("/{id}")
    // ResponseEntity<Map<String, Object>> update(@PathVariable Integer id,
    // @RequestBody R request);

    // @DeleteMapping("/{id}")
    // ResponseEntity<Boolean> delete(@PathVariable Integer id);

}
