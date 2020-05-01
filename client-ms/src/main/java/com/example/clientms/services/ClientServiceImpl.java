package com.example.clientms.services;

import com.example.clientms.entity.Client;
import com.example.clientms.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientMapper clientMapper;

    @Override
    public List<Client> getClientList() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.getOne(id);
    }

    @Override
    public Client newClient(ClientDTO clientDTO) {
        Client client = clientMapper.toDto(clientDTO);
        return clientRepository.save(client);
    }

    @Override
    public Client loadProfil(String login) {
        Client client = clientRepository.findClientByLogin(login);
        if (client == null) throw new RuntimeException("User not found");
        return client;
    }


    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);

    }
}
