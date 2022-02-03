package com.mindhub.homebanking.dtos;

public class loanApplicationDTO {
    private long id;
    private String name;
    private Double amount;
    private Double percentage;
    private int payments;
    private String accountDestiny;
    private Double balance;

    public loanApplicationDTO() {
    }

    public loanApplicationDTO(long id, String name, Double amount, Double percentage, int payments, String accountDestiny, Double balance) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.percentage = percentage;
        this.payments = payments;
        this.accountDestiny = accountDestiny;
        this.balance= balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public String getAccountDestiny() {
        return accountDestiny;
    }

    public void setAccountDestiny(String accountDestiny) {
        this.accountDestiny = accountDestiny;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
