package com.projet7.ZuulGateway.DAO;

import com.projet7.ZuulGateway.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {
    Roles findByName(String name);
}
