package com.example.clientms.repository;

import com.example.clientms.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findClientByLogin(String login);
}
