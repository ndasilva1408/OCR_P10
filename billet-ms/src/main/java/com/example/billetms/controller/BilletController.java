package com.example.billetms.controller;

import com.example.billetms.entities.Billet;
import com.example.billetms.services.BilletDTO;
import com.example.billetms.services.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BilletController {
    @Autowired
    private BilletService billetService;

    @GetMapping(value = "/api/billet-microservice/getAll")
    public List<Billet> getBilletById() {
        return billetService.getAllBillets();
    }

    @GetMapping(value = "/api/billet-microservice/getBookerBillets")
    public List<Billet> getBilletsByBookerId(@RequestParam(name = "bookerId", defaultValue = "") String bookerId) {
        return billetService.getBilletsByBooker(bookerId);
    }

    @GetMapping(value = "/api/billet-microservice/getBillet")
    public ResponseEntity<Billet> getBillet(@RequestParam(name = "id", defaultValue = "") String id) {
        Billet billet = billetService.getBillet(Long.valueOf(id));
        if (billet == null) return ResponseEntity.noContent().build();
        return new ResponseEntity<>(billet, HttpStatus.OK);
    }

    @PostMapping(value = "/api/billet-microservice/addBillet")
    public ResponseEntity<Billet> createBillet(@RequestBody BilletDTO billetDTO) {
        Billet billet = billetService.createBillet(billetDTO);
        return new ResponseEntity<>(billet, HttpStatus.OK);
    }

    @PutMapping(value = "/api/billet-microservice/extendBillet")
    public ResponseEntity<Void> extendBillet(@RequestParam(name = "id") String id) {
        if (id != null && !id.isEmpty()) {
            billetService.updateBilletExtendStatus(Long.valueOf(id));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/billet-microservice/deleteBillet")
    public ResponseEntity<Void> deleteBillet(@RequestParam(name = "id", defaultValue = "") String id) {
        Billet billet = billetService.getBillet(Long.valueOf(id));
        if (billet == null)
            return ResponseEntity.noContent().build();
        billetService.deleteBillets(billet.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
