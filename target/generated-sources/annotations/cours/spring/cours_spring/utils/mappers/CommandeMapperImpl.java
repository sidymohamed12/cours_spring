package cours.spring.cours_spring.utils.mappers;

import cours.spring.cours_spring.data.entities.Client;
import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.web.dto.request.CommandeCreateRequest;
import cours.spring.cours_spring.web.dto.response.client.ClientSimpleResponse;
import cours.spring.cours_spring.web.dto.response.commande.CommandeOneResponse;
import cours.spring.cours_spring.web.dto.response.commande.CommandeSimpleResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-02T13:59:59+0000",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class CommandeMapperImpl implements CommandeMapper {

    @Override
    public Commande toEntity(CommandeCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Commande commande = new Commande();

        commande.setClient( mapClient( request.getClientId() ) );
        commande.setDate( request.getDate() );
        commande.setMontant( request.getMontant() );

        return commande;
    }

    @Override
    public CommandeSimpleResponse toSimpleResponse(Commande commande) {
        if ( commande == null ) {
            return null;
        }

        CommandeSimpleResponse commandeSimpleResponse = new CommandeSimpleResponse();

        commandeSimpleResponse.setClient( mapClientName( commande.getClient() ) );
        commandeSimpleResponse.setDate( commande.getDate() );
        commandeSimpleResponse.setId( commande.getId() );
        commandeSimpleResponse.setMontant( commande.getMontant() );

        return commandeSimpleResponse;
    }

    @Override
    public CommandeOneResponse toOneResponse(Commande commande) {
        if ( commande == null ) {
            return null;
        }

        CommandeOneResponse commandeOneResponse = new CommandeOneResponse();

        commandeOneResponse.setClient( clientToClientSimpleResponse( commande.getClient() ) );
        commandeOneResponse.setDate( commande.getDate() );
        commandeOneResponse.setId( commande.getId() );
        commandeOneResponse.setMontant( commande.getMontant() );

        return commandeOneResponse;
    }

    protected ClientSimpleResponse clientToClientSimpleResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientSimpleResponse clientSimpleResponse = new ClientSimpleResponse();

        clientSimpleResponse.setId( client.getId() );
        clientSimpleResponse.setName( client.getName() );
        clientSimpleResponse.setTelephone( client.getTelephone() );

        return clientSimpleResponse;
    }
}
