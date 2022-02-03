package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Turn;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class TurnDTO {
    private String name;
    private int shiftNumber;
    private LocalDateTime shiftDate;
    private LocalDateTime shiftTime;
    private String description;
    private Client client;


    public TurnDTO() {
    }

    public TurnDTO(Turn turn) {
        this.name = turn.getName();
        this.shiftNumber = turn.getShiftNumber();
        this.shiftDate = turn.getShiftDate();
        this.shiftTime = turn.getShiftTime();
        this.description = turn.getDescription();
        this.client = turn.getClient();
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

}
