package com.projet7.ZuulGateway.services;

import com.projet7.ZuulGateway.DAO.ClientRepository;
import com.projet7.ZuulGateway.DAO.RoleRepository;
import com.projet7.ZuulGateway.model.Client;
import com.projet7.ZuulGateway.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public Client getUser(Long id) {
        Client client = clientRepository.getOne(id);
        return client;
    }

    @Override
    public Client createUser(ClientDTO clientDTO) {
        if (clientDTO.getRoles()==null) {
            Set<Roles>roles = new HashSet<>();
            Roles roleClient = roleRepository.findByName("INVITE");
                    roles.add(roleClient);
            clientDTO.setRoles(roles);
        }
        Client client =new Client();
        client.setLogin(clientDTO.getLogin());
        client.setRoles(clientDTO.getRoles());
        client.setAdresse(clientDTO.getAdresse());
        client.setNom(clientDTO.getNom());
        client.setPrenom(clientDTO.getPrenom());
        client.setMail(clientDTO.getMail());
        client.setPassword(passwordEncoder.encode(clientDTO.getPassword()));

        return clientRepository.save(client);
    }

    @Override
    public void deleteUser(Long id) {
        clientRepository.deleteById(id);

    }
}
