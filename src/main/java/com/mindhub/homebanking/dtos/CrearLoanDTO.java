package com.mindhub.homebanking.dtos;

import java.util.ArrayList;
import java.util.List;

public class CrearLoanDTO {
    //ATRIBUTOS
    private String name;
    private double maxAmount;
    private Double percentage;
    private List<Integer> payments= new ArrayList<>();


    //CONSTRUCTORES
    public CrearLoanDTO() {
    }

    public CrearLoanDTO(String name, double maxAmount, Double percentage,  List<Integer> payments, double interest) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.percentage = percentage;
        this.payments = payments;

    }

    //GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }


}