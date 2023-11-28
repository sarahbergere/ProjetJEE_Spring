package marketplace.ProjetJ2EE_SpringBoot.service;

import marketplace.ProjetJ2EE_SpringBoot.repository.ClientRepository;
import marketplace.ProjetJ2EE_SpringBoot.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    public void saveOrUpdate(Client client) {
        clientRepository.save(client);
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
