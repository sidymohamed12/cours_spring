package cours.spring.cours_spring.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.data.repository.ICommandeRepository;
import cours.spring.cours_spring.services.ICommandeService;
import cours.spring.cours_spring.utils.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CommandeService implements ICommandeService {

    private final ICommandeRepository commandeRepository;

    @Override
    public Commande create(Commande value) {
        return commandeRepository.save(value);
    }

    @Override
    public List<Commande> getAll() {
        return commandeRepository.findAll();
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
    public Page<Commande> getAll(Pageable pageable) {
        return commandeRepository.findAll(pageable);
    }
}
