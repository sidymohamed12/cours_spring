package cours.spring.cours_spring.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cours.spring.cours_spring.config.Icontroller;
import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.web.dto.request.CommandeCreateRequest;
import cours.spring.cours_spring.web.dto.response.commande.CommandeOneResponse;

@RequestMapping("/commande")
public interface ICommandeController extends Icontroller<CommandeCreateRequest> {

    @PutMapping("/{id}")
    ResponseEntity<CommandeOneResponse> updateCommande(@PathVariable Integer id, @RequestBody Commande commande);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteCommande(@PathVariable Integer id);
}
