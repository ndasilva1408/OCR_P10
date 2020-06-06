package com.example.clientms.controller;

import com.example.clientms.entity.Client;
import com.example.clientms.services.ClientDTO;
import com.example.clientms.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping(value = "/api/client-microservice/getAll")
    public ResponseEntity<List<Client>> getClientList(){
        List<Client>clientList=clientService.getClientList();
        return  new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @GetMapping(value = "/api/client-microservice/getClient")
    public ResponseEntity<Client> getClient(@RequestParam(name = "id" , defaultValue = "") Long id){
        Client client = clientService.getClient(id);
    if(client==null) return  ResponseEntity.noContent().build();
    return new ResponseEntity<>(client,HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/api/client-microservice/getClientLogin")
    public ResponseEntity<String> getClientLogin(@RequestParam(name = "id" , defaultValue = "") Long id){
        String login = clientService.getClientLogin(id);
        if(login==null) return  ResponseEntity.noContent().build();
        return new ResponseEntity<>(login,HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/api/client-microservice/myProfil")
    public ResponseEntity<Client> loadProfil(@RequestParam(name = "login", defaultValue = "") String login){
        Client client = clientService.loadProfil(login);
        if(client==null) return ResponseEntity.noContent().build();
        return  new ResponseEntity<>(client,HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/api/client-microservice/myId")
    public ResponseEntity<Integer> loadId(@RequestParam(name = "login", defaultValue = "") String login){
        Integer clientId = clientService.getClientId(login);
        if(clientId==null) return ResponseEntity.noContent().build();
        return  new ResponseEntity<>(clientId,HttpStatus.ACCEPTED);
    }


  @DeleteMapping(value = "/api/client-microservice/deleteClient")
    public ResponseEntity<Void> deleteClient(@RequestParam(name = "id",defaultValue = "")Long id){
        Client client = clientService.getClient(id);
        if(client==null) return ResponseEntity .noContent().build();
        clientService.deleteClient(client.getId());
        return new ResponseEntity<>(HttpStatus.OK);
  }


}
