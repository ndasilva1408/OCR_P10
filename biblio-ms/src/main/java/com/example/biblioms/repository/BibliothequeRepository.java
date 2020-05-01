package com.example.biblioms.repository;

import com.example.biblioms.entity.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface BibliothequeRepository extends JpaRepository<Bibliotheque,Long> {

    Bibliotheque findBibliothequeById(Long id);
}
