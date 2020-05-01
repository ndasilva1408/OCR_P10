package com.projet7.ZuulGateway.controller;

import com.projet7.ZuulGateway.DAO.RoleRepository;
import com.projet7.ZuulGateway.message.request.LoginForm;
import com.projet7.ZuulGateway.message.response.JwtResponse;
import com.projet7.ZuulGateway.model.Client;
import com.projet7.ZuulGateway.services.ClientDTO;
import com.projet7.ZuulGateway.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.projet7.ZuulGateway.DAO.ClientRepository;
import com.projet7.ZuulGateway.jwt.JwtProvider;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthRestAPIs {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    private UserService userService;


    @PostMapping("/api/auth/signin")
    public ResponseEntity<?> authenticateUser (@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

return ResponseEntity.ok(
        new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }


    @PostMapping("/api/auth/signup")
    public ResponseEntity<Client> registerUser(@RequestBody ClientDTO clientDTO) {
  Client client = userService.createUser(clientDTO);
  if(client==null) return ResponseEntity.noContent().build();
  return new ResponseEntity<>(client, HttpStatus.CREATED);

    }

    @DeleteMapping("/api/auth/deleteClient")
            public ResponseEntity<Void> deleteClient ( @RequestParam(name = "id", defaultValue = "")String id) {
        Client client = userService.getUser(Long.valueOf(id));
        if (client==null) return ResponseEntity.noContent().build();
        userService.deleteUser(client.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    {

    }
}
