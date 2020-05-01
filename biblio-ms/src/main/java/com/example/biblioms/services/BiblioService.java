package com.example.biblioms.services;

import com.example.biblioms.entity.Bibliotheque;

import java.util.List;

public interface BiblioService {

    List<Bibliotheque> getBiblioList();

    Bibliotheque getBiblio(Long id);

    Bibliotheque createBiblio(BiblioDTO biblioDTO);

    void deleteLibrary(Long id);
}
