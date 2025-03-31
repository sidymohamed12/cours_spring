package cours.spring.cours_spring.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cours.spring.cours_spring.data.entities.Client;
import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.data.repository.IClientRepository;
import cours.spring.cours_spring.services.IClientService;
import cours.spring.cours_spring.utils.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ClientService implements IClientService {
    private final IClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        if (client.getCommandes() != null) {
            for (Commande commande : client.getCommandes()) {
                commande.setClient(client);
            }
        }
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client update(Integer id, Client client) {
        Client cl = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (cl != null) {
            cl.setName(client.getName());
            cl.setTelephone(client.getTelephone());
            clientRepository.save(cl);
        }
        return cl;
    }

    @Override
    public Client getById(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
    }

    @Override
    public Boolean delete(Integer id) {
        Client cl = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (cl != null) {
            clientRepository.delete(cl);
            return true;
        }
        return false;
    }

}
