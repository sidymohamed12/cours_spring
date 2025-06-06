package cours.spring.cours_spring.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.data.entities.Detail;
import cours.spring.cours_spring.data.repository.IClientRepository;
import cours.spring.cours_spring.data.repository.ICommandeRepository;
import cours.spring.cours_spring.services.ICommandeService;
import cours.spring.cours_spring.utils.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CommandeService implements ICommandeService {

    private final ICommandeRepository commandeRepository;
    private final IClientRepository clientRepository;

    @Override
    public Commande create(Commande value) {
        if (value.getDetails() != null) {
            for (Detail detail : value.getDetails()) {
                detail.setCommande(value);
            }
        }
        return commandeRepository.save(value);
    }

    @Override
    public List<Commande> getAll() {
        return commandeRepository.findAll();
    }

    @Override
    public Page<Commande> getAll(Pageable pageable) {
        return commandeRepository.findAll(pageable);
    }

    @Override
    public Commande update(Integer id, Commande value) {
        Commande data = commandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (data != null) {
            data.setClient(value.getClient());
            data.setMontant(value.getMontant());
            data.setDate(value.getDate());
            commandeRepository.save(data);
        }
        return data;
    }

    @Override
    public Commande getById(Integer id) {
        return commandeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
    }

    @Override
    public Boolean delete(Integer id) {
        var cmd = commandeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (cmd != null) {
            commandeRepository.delete(cmd);
            return true;
        }
        return false;
    }

    @Override
    public List<Commande> getCommandesByClientId(Integer clientId) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("l'id n'existe pas"));
        if (client == null)
            return null;
        return commandeRepository.findByClientId(client);
    }

}
