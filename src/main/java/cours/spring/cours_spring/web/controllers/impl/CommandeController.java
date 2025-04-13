package cours.spring.cours_spring.web.controllers.impl;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.data.entities.Detail;
import cours.spring.cours_spring.services.IArticleService;
import cours.spring.cours_spring.services.ICommandeService;
import cours.spring.cours_spring.utils.mappers.CommandeMapper;
import cours.spring.cours_spring.web.controllers.ICommandeController;
import cours.spring.cours_spring.web.dto.RestResponse;
import cours.spring.cours_spring.web.dto.request.CommandeCreateRequest;
import cours.spring.cours_spring.web.dto.request.DetailCreateRequest;
import cours.spring.cours_spring.web.dto.response.commande.CommandeOneResponse;
import cours.spring.cours_spring.web.dto.response.commande.CommandeSimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.var;

@RestController
@RequiredArgsConstructor
@Tag(name = "Commandes", description = "gestion des commandes")
public class CommandeController implements ICommandeController {

    private final ICommandeService commandeService;
    private final CommandeMapper commandeMapper;
    private final IArticleService articleService;

    @Override
    @Operation(summary = "récupérer toutes les commandes", description = "retourne une pagination de commandes ainsi que le nom du client")
    public ResponseEntity<Map<String, Object>> getAll(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var commandes = commandeService.getAll(pageable);
        var commandeResponse = commandes.map(commandeMapper::toSimpleResponse);
        var totalPages = commandeResponse.getTotalPages();
        return new ResponseEntity<>(RestResponse.responsePaginate(HttpStatus.OK,
                commandeResponse.getContent(), "CommandeSimpleResponse", new int[totalPages],
                commandeResponse.getNumber(), totalPages,
                commandeResponse.getTotalElements(), commandeResponse.isFirst(),
                commandeResponse.isLast()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommandeSimpleResponse>> getAllCommandes() {
        var commandes = commandeService.getAll();
        var commandeResponse = commandes.stream()
                .map(commandeMapper::toSimpleResponse)
                .toList();
        return new ResponseEntity<>(commandeResponse, HttpStatus.OK);
    }

    @Override
    @Operation(summary = "récupérer une commande par son id", description = "retourne une commande ainsi que le client")
    public ResponseEntity<Map<String, Object>> getOne(Integer id) {
        var commande = commandeService.getById(id);
        if (commande == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var response = commandeMapper.toOneResponse(commande);
        return new ResponseEntity<>(RestResponse.response(HttpStatus.OK, response, "CommandeOneResponse"),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> create(CommandeCreateRequest request, BindingResult bindingResult) {

        Commande commande = commandeMapper.toEntity(request);

        List<DetailCreateRequest> requestDetails = request.getDetails();
        List<Detail> commandeDetails = commande.getDetails();

        for (int i = 0; i < commandeDetails.size(); i++) {
            Detail detail = commandeDetails.get(i);
            DetailCreateRequest reqDetail = requestDetails.get(i);

            detail.setArticle(articleService.getById(reqDetail.getArticleId()));
        }

        var data = commandeService.create(commande);
        var response = commandeMapper.toOneResponse(data);
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.CREATED, response, "CommandeOneResponse"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommandeOneResponse> updateCommande(Integer id, Commande commande) {
        var data = commandeService.update(id, commande);
        var response = commandeMapper.toOneResponse(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteCommande(Integer id) {
        var data = commandeService.delete(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommandeSimpleResponse>> getCommandeByClientId(Integer id) {
        var client = commandeService.getCommandesByClientId(id);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var commandes = commandeService.getCommandesByClientId(id);
        var commandeResponse = commandes.stream()
                .map(commandeMapper::toSimpleResponse)
                .toList();
        return new ResponseEntity<>(commandeResponse, HttpStatus.OK);
    }

}
