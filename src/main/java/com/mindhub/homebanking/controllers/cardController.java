package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repository.CardRepository;
import com.mindhub.homebanking.repository.clientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Random;

@RestController
public class cardController {
    @Autowired
    private clientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/api/clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication, @RequestParam CardType type, @RequestParam CardColor color){

        Client client = clientRepository.findByEmail(authentication.getName());

        if( client.getCards().stream().filter(e -> e.getType().toString().equals(type.toString()) ).count() > 2) {
            return new ResponseEntity<>("403 Ya tiene 3 tarjetas de ese tipo", HttpStatus.FORBIDDEN);
        }
        Random rnd=new Random();
        int aleatorio=rnd.nextInt(99999);
        cardRepository.save(new Card(aleatorio,client.getFirstName()+ " " + client.getLastName(), type, color, NumberRandom.getCardNumber(1000,9999), NumberRandom.getRandomNumber(100,999), LocalDateTime.now(), LocalDateTime.now().plusYears(5), LocalDateTime.now(),client));
        return new ResponseEntity<>("201 Tarjeta Creada", HttpStatus.CREATED);
    }
    @DeleteMapping("/api/cards/delete/")
    public ResponseEntity<?> deleteCard(Authentication authentication, @RequestParam String number){

        Client client = clientRepository.findByEmail(authentication.getName());

        if (!client.getCards().contains(cardRepository.findByNumber(number))){
            return new ResponseEntity<>("Tarjeta no pertenece al cliente", HttpStatus.FORBIDDEN);
        }

        if(cardRepository.findByNumber(number) == null){
            return new ResponseEntity<>("Tarjeta inexistente", HttpStatus.FORBIDDEN);
        }

        cardRepository.delete(cardRepository.findByNumber(number));

        return new ResponseEntity<>("Tarjeta eliminada correctamente", HttpStatus.ACCEPTED);
    }
}
