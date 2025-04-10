package cours.spring.cours_spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cours.spring.cours_spring.data.entities.Client;

public interface IClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByName(String name);
}
