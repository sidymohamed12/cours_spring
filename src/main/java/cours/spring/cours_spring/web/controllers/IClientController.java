package cours.spring.cours_spring.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import cours.spring.cours_spring.config.Icontroller;
import cours.spring.cours_spring.data.entities.Client;
import cours.spring.cours_spring.web.dto.request.ClientSimpleCreateRequest;
import cours.spring.cours_spring.web.dto.response.client.ClientSimpleResponse;
import cours.spring.cours_spring.web.dto.response.client.ClientWithCommandeResponse;

@RequestMapping("/client")
public interface IClientController extends Icontroller<ClientSimpleCreateRequest> {

        @PutMapping("/{id}")
        ResponseEntity<ClientSimpleResponse> updateClient(@PathVariable Integer id, @RequestBody Client client);

        @DeleteMapping("/{id}")
        ResponseEntity<Boolean> deleteClient(@PathVariable Integer id);

        @GetMapping("/{id}/commandes")
        ResponseEntity<ClientWithCommandeResponse> getCientWithCommande(@PathVariable Integer id);
}
