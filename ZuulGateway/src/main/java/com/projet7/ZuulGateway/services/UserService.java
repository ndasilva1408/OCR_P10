package com.projet7.ZuulGateway.services;

import com.projet7.ZuulGateway.model.Client;

public interface UserService {
    Client getUser(Long id);

    Client createUser(ClientDTO clientDTO);

    void deleteUser(Long id);
}
