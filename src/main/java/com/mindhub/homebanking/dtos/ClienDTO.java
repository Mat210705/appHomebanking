package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;

    Set<AccountDTO> accounts = new HashSet<>();
    Set<ClientLoanDTO> loan = new HashSet<>();
    Set<CardDTO> card = new HashSet<>();

    public ClienDTO() {
    }

    public  ClienDTO(Client client) {

        this.id = client.getId();

        this.firstName = client.getFirstName();

        this.lastName = client.getLastName();

        this.email = client.getEmail();

        this.accounts = client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(Collectors.toSet());

        this.loan = client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());

        this.card = client.getCards().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public Set<ClientLoanDTO> getLoan() {
        return loan;
    }

    public void setLoan(Set<ClientLoanDTO> loan) {
        this.loan = loan;
    }

    public Set<CardDTO> getCard() {
        return card;
    }

    public void setCard(Set<CardDTO> card) {
        this.card = card;
    }
}
