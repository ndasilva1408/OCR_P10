package com.example.biblioms.controller;

import com.example.biblioms.entity.Bibliotheque;
import com.example.biblioms.services.BiblioDTO;
import com.example.biblioms.services.BiblioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BiblioController {
    @Autowired
    private BiblioService biblioService;

    @GetMapping(value = "/api/bibliotheque-microservice/getAll")
    public ResponseEntity<List<Bibliotheque>> getBiblios() {
        List<Bibliotheque> biblioList = biblioService.getBiblioList();
        if (biblioList == null) return ResponseEntity.noContent().build();
        return new ResponseEntity<>(biblioList, HttpStatus.OK);
    }

    @GetMapping(value = "/api/bibliotheque-microservice/getBiblio")
    public ResponseEntity<Bibliotheque> getBiblio(@RequestParam(name = "id", defaultValue = "") Long id) {
    Bibliotheque bibliotheque = biblioService. getBiblio(id);
    if(bibliotheque == null) return  ResponseEntity.noContent().build();
    return new ResponseEntity<>(bibliotheque,HttpStatus.OK);
    }

    @PostMapping(value = "/api/bibliotheque-microservice/addBiblio")
    public ResponseEntity<Bibliotheque> createBiblio(@RequestBody BiblioDTO biblioDTO) {
        Bibliotheque bibliotheque = biblioService.createBiblio(biblioDTO);
        if(bibliotheque == null) return ResponseEntity.noContent().build();
        return new ResponseEntity<>(bibliotheque,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/api/bibliotheque-microservice/deleteBiblio")
    public ResponseEntity<Void> deleteBiblio(@RequestParam(name="id",defaultValue = "")Long id){
        Bibliotheque bibliotheque = biblioService.getBiblio(id);
        if(bibliotheque == null) return ResponseEntity.noContent().build();
        biblioService.deleteLibrary(bibliotheque.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
