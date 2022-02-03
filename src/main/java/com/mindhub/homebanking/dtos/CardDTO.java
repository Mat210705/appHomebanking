package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


public class CardDTO extends Card {
    private long id;

    private String CardHolder;
    private CardType type;
    private CardColor color;
    private String number;
    private int cvv;
    private LocalDateTime FromDate;
    private LocalDateTime TrhuDate;
    private LocalDateTime Today;
    private Client client;

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.id = card.getId();
        this.CardHolder = card.getCardHolder();
        this.type = card.getType();
        this.color = card.getColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.FromDate = card.getFromDate();
        this.TrhuDate = card.getTrhuDate();
        this.Today = card.getToday();
        this.client = card.getClient();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getCardHolder() {
        return CardHolder;
    }

    @Override
    public void setCardHolder(String cardHolder) {
        CardHolder = cardHolder;
    }

    @Override
    public CardType getType() {
        return type;
    }

    @Override
    public void setType(CardType type) {
        this.type = type;
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public void setColor(CardColor color) {
        this.color = color;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int getCvv() {
        return cvv;
    }

    @Override
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public LocalDateTime getFromDate() {
        return FromDate;
    }

    @Override
    public void setFromDate(LocalDateTime fromDate) {
        FromDate = fromDate;
    }

    @Override
    public LocalDateTime getTrhuDate() {
        return TrhuDate;
    }

    @Override
    public void setTrhuDate(LocalDateTime trhuDate) {
        TrhuDate = trhuDate;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public LocalDateTime getToday() {
        return Today;
    }

    @Override
    public void setToday(LocalDateTime today) {
        Today = today;
    }
}
