package cours.spring.cours_spring.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cours.spring.cours_spring.config.IService;
import cours.spring.cours_spring.data.entities.Commande;

public interface ICommandeService extends IService<Commande> {
    Page<Commande> getAll(Pageable pageable);

    List<Commande> getCommandesByClientId(Integer clientId);
}
