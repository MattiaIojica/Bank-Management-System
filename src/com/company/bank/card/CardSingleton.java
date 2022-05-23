package com.company.bank.card;


public class CardSingleton {
    private static int id = 0;

    public Card addCard(String IBAN){
        return new Card(id++, IBAN);
    }


}
