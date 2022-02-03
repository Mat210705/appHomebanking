package com.mindhub.homebanking.dtos;


import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class TransactionDTO {
    private long id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime date;
    private Double balance;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id")
    private AccountDTO owner;

    public TransactionDTO() {
    }
    public TransactionDTO(Transaction transaction) {

        this.id = transaction.getId();

        this.type = transaction.getType();

        this.amount = transaction.getAmount();

        this.description = transaction.getDescription();

        this.date = transaction.getDate();

        this.balance = transaction.getBalance();



    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public AccountDTO getOwner() {
        return owner;
    }

    public void setOwner(AccountDTO owner) {
        this.owner = owner;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
