package com.mindhub.homebanking.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String CardHolder;
    private CardType type;
    private CardColor color;
    private String number;
    private int cvv;
    private LocalDateTime FromDate;
    private LocalDateTime TrhuDate;
    private LocalDateTime Today;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Cient_id")
    private Client client;

    public Card() {
    }

    public Card(long id, String cardHolder, CardType type, CardColor color, String number, int cvv, LocalDateTime fromDate, LocalDateTime trhuDate, LocalDateTime today, Client client) {
        this.id= getId();
        CardHolder = cardHolder;
        this.type = type;
        this.color = color;
        this.number = number;
        this.cvv = cvv;
        FromDate = fromDate;
        TrhuDate = trhuDate;
        this.client = client;
        Today = today;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardHolder() {
        return CardHolder;
    }

    public void setCardHolder(String cardHolder) {
        CardHolder = cardHolder;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDateTime getFromDate() {
        return FromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        FromDate = fromDate;
    }

    public LocalDateTime getTrhuDate() {
        return TrhuDate;
    }

    public void setTrhuDate(LocalDateTime trhuDate) {
        TrhuDate = trhuDate;
    }

    public LocalDateTime getToday() {
        return Today;
    }

    public void setToday(LocalDateTime today) {
        Today = today;
    }

    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}

