package cours.spring.cours_spring.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cours.spring.cours_spring.config.IService;
import cours.spring.cours_spring.data.entities.Client;

public interface IClientService extends IService<Client> {
    Page<Client> getAll(Pageable pageable);
}
