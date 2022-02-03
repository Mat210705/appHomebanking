package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.dtos.TurnDTO;
import com.mindhub.homebanking.dtos.loanApplicationDTO;
import com.mindhub.homebanking.dtos.turnApplicationDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repository.AccountRepository;
import com.mindhub.homebanking.repository.TurnRepository;
import com.mindhub.homebanking.repository.clientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class turnController {
    @Autowired
    private TurnRepository turnRepository;
    @Autowired
    private clientRepository clientRepository;


    @PostMapping("/api/clients/current/turn")
    public ResponseEntity<?> CreateNewTurn (Authentication authentication, @RequestBody turnApplicationDTO turnApplicationDTO){

            Client client = clientRepository.findByEmail(authentication.getName());
            Turn turn = turnRepository.findByName(turnApplicationDTO.getName());

        if (turnApplicationDTO.getShiftDate().toString().isEmpty() || turnApplicationDTO.getShiftTime().toString().isEmpty() || turnApplicationDTO.getDescription().isEmpty() || turnApplicationDTO.getShiftNumber() == 0) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (turnApplicationDTO.getClient() == null){
            return new ResponseEntity<>("El Cliente no Existe", HttpStatus.FORBIDDEN);
        }
        if(turnApplicationDTO.getShiftNumber() == turnApplicationDTO.getShiftNumber()){
            return new ResponseEntity<>("Hay dos turnos asignados iguales", HttpStatus.FORBIDDEN);
        }

        turnRepository.save(new Turn(turnApplicationDTO.getName(), turnApplicationDTO.getShiftNumber(), turnApplicationDTO.getShiftDate(), turnApplicationDTO.getShiftTime(), turnApplicationDTO.getDescription(), turnApplicationDTO.getClient()));
        return new ResponseEntity<>("El turno se asigno de manera exitosa", HttpStatus.ACCEPTED);
    }
    @GetMapping("api/clients/current/turn")
    public List<TurnDTO> getTurns (){
        return this.turnRepository.findAll().stream().map(TurnDTO::new).collect(Collectors.toList());
    }

}

