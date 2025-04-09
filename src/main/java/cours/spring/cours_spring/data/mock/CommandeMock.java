package cours.spring.cours_spring.data.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cours.spring.cours_spring.data.entities.Commande;
import cours.spring.cours_spring.data.entities.Client;
import cours.spring.cours_spring.data.repository.IClientRepository;
import cours.spring.cours_spring.data.repository.ICommandeRepository;
import lombok.RequiredArgsConstructor;

// @Component
// @Order(4)
@RequiredArgsConstructor
public class CommandeMock implements CommandLineRunner {

    private final ICommandeRepository commandeRepository;
    private final IClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Client> clients = clientRepository.findAll();
        List<Commande> commandes = new ArrayList<>();

        for (Client cl : clients) {
            for (int i = 1; i < 5; i++) {
                Commande commande = new Commande();
                commande.setClient(cl);
                commande.setMontant(1000.0 * i);
                commandes.add(commande);
            }
        }
        commandeRepository.saveAllAndFlush(commandes);
    }

}
