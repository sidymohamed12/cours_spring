package cours.spring.cours_spring.data.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cours.spring.cours_spring.data.entities.Client;
import cours.spring.cours_spring.data.repository.IClientRepository;
import lombok.RequiredArgsConstructor;

@Component
@Order(1)
@RequiredArgsConstructor
public class ClientMock implements CommandLineRunner {

    private final IClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Client client = new Client();
            client.setName("client_" + i);
            client.setTelephone("telephone_" + i);
            clients.add(client);
        }
        clientRepository.saveAllAndFlush(clients);
    }

}
