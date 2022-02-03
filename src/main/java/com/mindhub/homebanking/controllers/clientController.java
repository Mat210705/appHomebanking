package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClienDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repository.AccountRepository;
import com.mindhub.homebanking.repository.clientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController

public class clientController {
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private clientRepository clientRepository;

        @Autowired
        private AccountRepository accountRepository;

        @RequestMapping("/api/clients")
        public List<ClienDTO> getClients() {

                return clientRepository.findAll().stream().map(ClienDTO::new).collect(Collectors.toList());
        }

        @RequestMapping("/api/clients/{id}")
        public ClienDTO getClient(@PathVariable long id) {

                return clientRepository.findById(id).map(ClienDTO::new).orElse(null);
        }

                @RequestMapping(path = "/api/clients", method = RequestMethod.POST)
                public ResponseEntity<Object> register(

                        @RequestParam String firstName, @RequestParam String lastName,

                        @RequestParam String email, @RequestParam String password) {


                        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

                                return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
                        }

                        if (clientRepository.findByEmail(email) != null) {

                                return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
                        }

                        Client newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password));
                        clientRepository.save(newClient);

                        Random rnd=new Random();
                        int aleatorio=rnd.nextInt(99999999);
                        Account newAccount = new Account("VIN" + aleatorio, AccountType.Ahorro, LocalDateTime.now(),0.0);


                        newClient.addAccount(newAccount);
                        accountRepository.save(newAccount);


                        return new ResponseEntity<>(HttpStatus.CREATED);

                }

                @RequestMapping("/api/clients/current")

                public ClienDTO getClientDto(Authentication authentication) {

                        return new ClienDTO(clientRepository.findByEmail(authentication.getName()));


                }

        }


