package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.CrearLoanDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.dtos.loanApplicationDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class loanController {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    ClientLoanRepository clientLoanRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    clientRepository clientRepository;



    @Transactional
    @PostMapping("/api/loans")
    public ResponseEntity<?> CreateNewLoans (Authentication authentication, @RequestBody loanApplicationDTO loanApplicationDTO){


        Loan loan = loanRepository.findByName(loanApplicationDTO.getName());
        Account account = accountRepository.findByNumber(loanApplicationDTO.getAccountDestiny());
        Client client = clientRepository.findByEmail(authentication.getName());


        if( loanApplicationDTO.getAmount().toString().isEmpty() || loanApplicationDTO.getAmount() <= 0 || loanApplicationDTO.getPayments() <= 0){
            return new ResponseEntity<>("El nombre, la monto maximo o numero de cuotas estan vacios", HttpStatus.FORBIDDEN);
        }
        if(loanApplicationDTO.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("El monto es mayor al monto solicitado", HttpStatus.FORBIDDEN);
        }
        if (loan == null){
            return new ResponseEntity<>("El prestamo solicitado no existe", HttpStatus.FORBIDDEN);
        }
        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())){
            return new ResponseEntity<>("Cantidad de cuotas incorrectas", HttpStatus.FORBIDDEN);
        }
        if (account == null){
            return new ResponseEntity<>("Cuenta destino incorrecta", HttpStatus.FORBIDDEN);
        }
        if (!client.getAccounts().contains(account)){
            return new ResponseEntity<>("Cuenta destino no pertenece al cliente", HttpStatus.FORBIDDEN);
        }


        clientLoanRepository.save(new ClientLoan(loanApplicationDTO.getAmount(), loanApplicationDTO.getPayments(), client, loan, account.getNumber() ));
        transactionRepository.save(new Transaction(TransactionType.Credit, loanApplicationDTO.getAmount(), "Préstamo - " + loan.getName(), LocalDateTime.now(), account, loanApplicationDTO.getBalance()+loanApplicationDTO.getAmount()));
        account.setBalance(account.getBalance() + loanApplicationDTO.getAmount());
        accountRepository.save(account);
        return new ResponseEntity<>("Se a Actualizado los datos de la cuenta, su prestamo a sido realizado", HttpStatus.ACCEPTED);
    }
    @GetMapping("api/loans")
    public List<LoanDTO> getLoans (){
        return this.loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toList());
    }
    @PostMapping("/api/admin/loan")
    public ResponseEntity<?> createLoan(Authentication authentication, @RequestBody CrearLoanDTO crearLoanDTO){
        Client client = clientRepository.findByEmail(authentication.getName());

        if (!client.getEmail().contains("@admin.com")){
            return new ResponseEntity<>("Not authorized", HttpStatus.FORBIDDEN);
        }
        if (crearLoanDTO.getPercentage() == 0 || crearLoanDTO.getMaxAmount() == 0 || crearLoanDTO.getName().isEmpty() || crearLoanDTO.getPayments().isEmpty()){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        loanRepository.save(new Loan(crearLoanDTO.getName(), crearLoanDTO.getMaxAmount(), crearLoanDTO.getPercentage(), crearLoanDTO.getPayments()));
        return new ResponseEntity<>("New Loan Added", HttpStatus.CREATED);
    }
}
