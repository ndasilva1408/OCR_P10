package com.projet7.ZuulGateway.services;

import com.projet7.ZuulGateway.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.projet7.ZuulGateway.DAO.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        Client client = clientRepository.findByLogin(login)
                .orElseThrow(()->
                        new UsernameNotFoundException("Client Not Found"));
        return  UserPrinciple.build(client);

    }
}
