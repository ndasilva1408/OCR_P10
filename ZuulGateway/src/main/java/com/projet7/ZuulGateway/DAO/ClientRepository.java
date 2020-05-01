package com.projet7.ZuulGateway.DAO;
import com.projet7.ZuulGateway.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin
public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByLogin(String login);
    Boolean existsByLogin(String login);
}
