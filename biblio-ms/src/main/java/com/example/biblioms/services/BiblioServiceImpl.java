package com.example.biblioms.services;

import com.example.biblioms.entity.Bibliotheque;
import com.example.biblioms.repository.BibliothequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiblioServiceImpl implements BiblioService {
    @Autowired
    BibliothequeRepository bibliothequeRepository;
    @Autowired
    BiblioMapper biblioMapper;

    @Override
    public List<Bibliotheque> getBiblioList() {
        return bibliothequeRepository.findAll();
    }

    @Override
    public Bibliotheque getBiblio(Long id) {
        return bibliothequeRepository.getOne(id);
    }

    @Override
    public Bibliotheque createBiblio(BiblioDTO biblioDTO) {
        Bibliotheque bibliotheque = biblioMapper.toDTO(biblioDTO);

        return bibliothequeRepository.save(bibliotheque) ;
    }

    @Override
    public void deleteLibrary(Long id) {
        bibliothequeRepository.deleteById(id);

    }
}
