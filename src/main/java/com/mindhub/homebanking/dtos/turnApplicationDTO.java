package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Client;

import java.time.LocalDateTime;

public class turnApplicationDTO {
    private String name;
    private int shiftNumber;
    private LocalDateTime shiftDate;
    private LocalDateTime shiftTime;
    private String description;
    private Client client;

    public turnApplicationDTO() {
    }

    public turnApplicationDTO(String name, int shiftNumber, LocalDateTime shiftDate, LocalDateTime shiftTime, String description, Client client) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
