package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClienDTO;
import com.mindhub.homebanking.dtos.PaymentsDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class paymentsController {

    @Autowired
    clientRepository clientRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    @Transactional
    @PostMapping("/payment")
    public ResponseEntity<?> payment(@RequestBody PaymentsDTO paymentDTO) {

        if(paymentDTO.getDescription().isEmpty() || paymentDTO.getAmount() < 1 ||paymentDTO.getCvv() == 0 ||  paymentDTO.getNumber().isEmpty() ){
            return new ResponseEntity<>("Datos incorrectos", HttpStatus.FORBIDDEN);
        }
        Card card = cardRepository.findByNumber(paymentDTO.getNumber());
        if(card == null){
            return new ResponseEntity<>("No se encontró tarjeta", HttpStatus.FORBIDDEN);
        }

        Client client = clientRepository.findByCards(card);
        if(client == null){
            return new ResponseEntity<>("No se encontró cliente", HttpStatus.FORBIDDEN);
        }

        if(card.getCvv() != paymentDTO.getCvv()){
            return new ResponseEntity<>("Datos incorrectos - CVV",HttpStatus.FORBIDDEN);
        }

        List<Account> accounts = new ArrayList<>(client.getAccounts());
        Account account = accounts.stream().filter(a -> a.getBalance() >= paymentDTO.getAmount()).findFirst().get();

        if(account.getNumber() == null){
            return new ResponseEntity<>("Saldo insuficiente", HttpStatus.FORBIDDEN);
        }

        transactionRepository.save(new Transaction(TransactionType.Debit, - paymentDTO.getAmount(), "Pago - " + paymentDTO.getDescription(), LocalDateTime.now(), account, account.getBalance()));
        account.setBalance(account.getBalance() - paymentDTO.getAmount());
        accountRepository.save(account);

        return new ResponseEntity<>("Pago procesado correctamente", HttpStatus.ACCEPTED);
    }
}