package com.example.billetms.controller;

import com.example.billetms.entities.Billet;
import com.example.billetms.repository.BilletRepository;
import com.example.billetms.services.BilletDTO;
import com.example.billetms.services.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BilletController {
    @Autowired
    private BilletService billetService;
    @Autowired
    private BilletRepository billetRepository;


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

    @PostMapping(value = "/api/billet-microservice/addBilletForWaitList")
    public ResponseEntity<Billet> createBilletForWaitList(@RequestBody BilletDTO billetDTO) {
        Billet billet = billetService.createBilletForWaitList(billetDTO);
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

    @GetMapping(value = "/api/billet-microservice/getWaitingList")
    public ResponseEntity<ArrayList<Billet>> getWaitingList(@RequestParam(name = "waitingListSize", defaultValue = "") Integer waitingListSize,
                                                            @RequestParam(name = "id", defaultValue = "") String id) {
        ArrayList<Billet> newWaitingList = new ArrayList<>();
        List<Billet> billetList = billetService.getBilletsByBook(id);
        for (int i = 0; i < billetList.size(); i++)
            if (billetList.get(i).getIsOnWaitList()) {
                newWaitingList.add(billetList.get(i));
            }
        newWaitingList.sort(Comparator.comparingLong(Billet::getId));
        return new ResponseEntity<>(newWaitingList, HttpStatus.OK);
    }

    @GetMapping(value = "/api/billet-microservice/canBorrow")
    public ResponseEntity<Boolean> updateCanBorrow(@RequestParam(name = "waitinList", defaultValue = "") ArrayList<Billet> waitinList,
                                                   @RequestParam(name = "bookId", defaultValue = "") String bookId,
                                                   @RequestParam(name = "userId", defaultValue = "") String userId) {
        Boolean cantBorrow = Boolean.FALSE;

        for (int i = 0; i < waitinList.size(); i++)
            if (waitinList.get(i).getBookId().equals(bookId)) {
                if (waitinList.get(i).getBookerId().equals(userId) && waitinList.get(i).getIsOnWaitList()) {
                    cantBorrow = true;
                } else {
                    cantBorrow = false;
                }
            }

        return new ResponseEntity<>(cantBorrow, HttpStatus.OK);
    }


}
