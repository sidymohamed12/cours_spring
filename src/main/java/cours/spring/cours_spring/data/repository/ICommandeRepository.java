package cours.spring.cours_spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cours.spring.cours_spring.data.entities.Commande;

public interface ICommandeRepository extends JpaRepository<Commande, Integer> {

}
