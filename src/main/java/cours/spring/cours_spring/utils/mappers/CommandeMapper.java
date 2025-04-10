package cours.spring.cours_spring.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import cours.spring.cours_spring.data.entities.Client;
import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.web.dto.request.CommandeCreateRequest;
import cours.spring.cours_spring.web.dto.response.commande.CommandeOneResponse;
import cours.spring.cours_spring.web.dto.response.commande.CommandeSimpleResponse;

@Mapper(componentModel = "spring")
public interface CommandeMapper {
    CommandeMapper INSTANCE = Mappers.getMapper(CommandeMapper.class);

    @Mapping(source = "clientId", target = "client.id")
    Commande toEntity(CommandeCreateRequest request);

    @Mapping(source = "client", target = "client")
    CommandeSimpleResponse toSimpleResponse(Commande commande);

    @Mapping(source = "client", target = "client")
    CommandeOneResponse toOneResponse(Commande commande);

    default String mapClientName(Client client) {
        return client != null ? client.getName() : null;
    }

    // default Client mapClient(Integer id) {
    // if (id == null) {
    // return null;
    // }
    // Client client = new Client();
    // client.setId(id);
    // return client;
    // }
}
