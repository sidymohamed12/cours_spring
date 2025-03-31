package cours.spring.cours_spring.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import cours.spring.cours_spring.data.entities.Client;
import cours.spring.cours_spring.web.dto.request.ClientAndCommandeCreateRequest;
import cours.spring.cours_spring.web.dto.request.ClientSimpleCreateRequest;
import cours.spring.cours_spring.web.dto.response.client.ClientSimpleResponse;
import cours.spring.cours_spring.web.dto.response.client.ClientWithCommandeResponse;

@Mapper(componentModel = "spring", uses = { CommandeMapper.class })
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "commandes", target = "commandes")
    Client toEntity(ClientAndCommandeCreateRequest request);

    Client toEntity1(ClientSimpleCreateRequest request);

    ClientSimpleResponse toSimpleResponse(Client client);

    @Mapping(source = "commandes", target = "commandes")
    ClientWithCommandeResponse ResponseClientWithCommande(Client client);
}