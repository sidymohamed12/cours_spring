package cours.spring.cours_spring.utils.mappers;

import cours.spring.cours_spring.data.entities.Client;
import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.web.dto.request.ClientAndCommandeCreateRequest;
import cours.spring.cours_spring.web.dto.request.ClientSimpleCreateRequest;
import cours.spring.cours_spring.web.dto.request.CommandeCreateRequest;
import cours.spring.cours_spring.web.dto.response.client.ClientSimpleResponse;
import cours.spring.cours_spring.web.dto.response.client.ClientWithCommandeResponse;
import cours.spring.cours_spring.web.dto.response.commande.CommandeSimpleResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-31T11:07:35+0000",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250325-2231, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Autowired
    private CommandeMapper commandeMapper;

    @Override
    public Client toEntity(ClientAndCommandeCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Client client = new Client();

        client.setCommandes( commandeCreateRequestListToCommandeList( request.getCommandes() ) );
        client.setName( request.getName() );
        client.setTelephone( request.getTelephone() );

        return client;
    }

    @Override
    public Client toEntity1(ClientSimpleCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Client client = new Client();

        client.setName( request.getName() );
        client.setTelephone( request.getTelephone() );

        return client;
    }

    @Override
    public ClientSimpleResponse toSimpleResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientSimpleResponse clientSimpleResponse = new ClientSimpleResponse();

        clientSimpleResponse.setId( client.getId() );
        clientSimpleResponse.setName( client.getName() );
        clientSimpleResponse.setTelephone( client.getTelephone() );

        return clientSimpleResponse;
    }

    @Override
    public ClientWithCommandeResponse ResponseClientWithCommande(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientWithCommandeResponse clientWithCommandeResponse = new ClientWithCommandeResponse();

        clientWithCommandeResponse.setCommandes( commandeListToCommandeSimpleResponseList( client.getCommandes() ) );
        clientWithCommandeResponse.setId( client.getId() );
        clientWithCommandeResponse.setName( client.getName() );
        clientWithCommandeResponse.setTelephone( client.getTelephone() );

        return clientWithCommandeResponse;
    }

    protected List<Commande> commandeCreateRequestListToCommandeList(List<CommandeCreateRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<Commande> list1 = new ArrayList<Commande>( list.size() );
        for ( CommandeCreateRequest commandeCreateRequest : list ) {
            list1.add( commandeMapper.toEntity( commandeCreateRequest ) );
        }

        return list1;
    }

    protected List<CommandeSimpleResponse> commandeListToCommandeSimpleResponseList(List<Commande> list) {
        if ( list == null ) {
            return null;
        }

        List<CommandeSimpleResponse> list1 = new ArrayList<CommandeSimpleResponse>( list.size() );
        for ( Commande commande : list ) {
            list1.add( commandeMapper.toSimpleResponse( commande ) );
        }

        return list1;
    }
}
