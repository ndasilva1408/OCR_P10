package com.example.clientms.services;

import com.example.clientms.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> getClientList();

    Client getClient(Long id);

    Client newClient (ClientDTO clientDTO);

    Client loadProfil (String login);

    String getClientLogin (Long id);

    Integer getClientId (String login);


    void deleteClient (Long id);
}
