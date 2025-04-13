package cours.spring.cours_spring.web.controllers.impl;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import cours.spring.cours_spring.data.entities.Client;
import cours.spring.cours_spring.services.IClientService;
import cours.spring.cours_spring.utils.mappers.ClientMapper;
import cours.spring.cours_spring.web.controllers.IClientController;
import cours.spring.cours_spring.web.dto.RestResponse;
import cours.spring.cours_spring.web.dto.request.ClientSimpleCreateRequest;
import cours.spring.cours_spring.web.dto.response.client.ClientSimpleResponse;
import cours.spring.cours_spring.web.dto.response.client.ClientWithCommandeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Clients", description = "gestions des clients")
public class ClientController implements IClientController {

    private final IClientService clientService;
    private final ClientMapper clientMapper;

    @Override
    @Operation(summary = "récupérer tous les clients", description = "retourne une pagination de clients")
    public ResponseEntity<Map<String, Object>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var clients = clientService.getAll(pageable);
        var clientResponse = clients.map(clientMapper::toSimpleResponse);
        var totalPages = clientResponse.getTotalPages();
        return new ResponseEntity<>(RestResponse.responsePaginate(
                HttpStatus.OK,
                clientResponse.getContent(), "clientResponse", new int[totalPages],
                clientResponse.getNumber(), totalPages,
                clientResponse.getTotalElements(),
                clientResponse.isFirst(), clientResponse.isLast()), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ClientWithCommandeResponse> getCientWithCommande(Integer id) {
        var client = clientService.getById(id);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var response = clientMapper.toResponseClientWithCommande(client);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Operation(summary = "récupérer un client par son id", description = "retourne un client ainsi que ses commandes")
    public ResponseEntity<Map<String, Object>> getOne(Integer id) {
        var client = clientService.getById(id);
        if (client == null) {
            return ResponseEntity.noContent().build();
        }
        var response = clientMapper.toResponseClientWithCommande(client);

        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.OK, response, "ClientWithCommande"),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> create(ClientSimpleCreateRequest client,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errors = RestResponse.extractFieldErrors(bindingResult);
            return new ResponseEntity<>(RestResponse.response(HttpStatus.BAD_REQUEST,
                    errors, "Map<String, String>"), HttpStatus.BAD_REQUEST);
        } else {
            var data = clientService.create(clientMapper.toEntity1(client));
            var response = clientMapper.toSimpleResponse(data);
            return new ResponseEntity<>(RestResponse.response(HttpStatus.CREATED,
                    response, "ClientSimpleResponse"), HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<ClientSimpleResponse> updateClient(Integer id, Client client) {
        var data = clientService.update(id, client);
        var response = clientMapper.toSimpleResponse(data);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Boolean> deleteClient(Integer id) {
        var client = clientService.delete(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
