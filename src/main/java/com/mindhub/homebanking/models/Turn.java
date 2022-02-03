package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private String name;
    private int shiftNumber;
    private LocalDateTime shiftDate;
    private LocalDateTime shiftTime;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Client_id")
    private Client client;

    public Turn() {
    }

    public Turn(String name,int shiftNumber, LocalDateTime shiftDate, LocalDateTime shiftTime, String description, Client client) {
        this.name = name;
        this.shiftNumber = shiftNumber;
        this.shiftDate = shiftDate;
        this.shiftTime = shiftTime;
        this.description = description;
        this.client = client;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public LocalDateTime getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDateTime shiftDate) {
        this.shiftDate = shiftDate;
    }

    public LocalDateTime getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(LocalDateTime shiftTime) {
        this.shiftTime = shiftTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
