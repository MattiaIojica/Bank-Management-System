package com.company.bank.card;


import com.company.user.ServiceUser;

import java.util.ArrayList;
import java.util.List;

public class ServiceCard {
    private List<Card> cards = new ArrayList<>();
    private static ServiceCard instance;

    public ServiceCard(){
    }

    public static ServiceCard getInstance() {
        if(instance == null)
            instance = new ServiceCard();
        return instance;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getCardByNumber(String number)
    {
        for (int i = 0; i < this.cards.size(); ++i)
            if (this.cards.get(i).getNumber().equals(number))
                return this.cards.get(i);

        return null;
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public void removeCard(Card card){
        this.cards.removeIf(x -> x.getNumber() == card.getNumber());
    }

}
