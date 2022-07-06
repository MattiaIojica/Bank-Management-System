package com.company.bank.bankaccount;

import com.company.bank.card.Card;
import com.company.bank.card.CardFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BankAccount {
    protected String IBAN;
    protected double balance;
    protected int ownerId;

    protected List<Card> cardList = new ArrayList<>();
    private final CardFactory cardFactory = CardFactory.getInstance();
    static private final Set<String> usedNumbers = new HashSet<>();

    public BankAccount(String IBAN, double balance, int ownerId){
        this.IBAN = IBAN;
        this.balance = balance;
        this.ownerId = ownerId;
    }

    public BankAccount(int ownerId) {
        this.IBAN = randomIBANGenerator();

        while(usedNumbers.contains(this.IBAN))
            this.IBAN = randomIBANGenerator();
        usedNumbers.add(this.IBAN);

        this.balance = 0;
        this.ownerId = ownerId;
    }

    public BankAccount(ResultSet in) throws SQLException {
        this.IBAN = in.getString("IBAN");
        this.balance = in.getDouble("balance");
        this.ownerId = in.getInt("ownerId");
    }


    public Card addCard(){
        Card card = cardFactory.addCard(this.IBAN);
        cardList.add(card);

        return card;
    }

    public void addCard(Card card){
        cardList.add(card);
    }

    //getters and setters


    public String getIBAN() {
        return IBAN;
    }

    public double getBalance() {
        return balance;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    static private String randomIBANGenerator(){
        Random rand = new Random();
        String code = "RO";
        for (int i = 0; i < 14; i++)
        {
            int n = rand.nextInt(10) + 0;
            code += Integer.toString(n);
        }

        return code;
    }


    @Override
    public String toString() {
        return "BankAccount{" +
                "IBAN='" + IBAN + '\'' +
                ", balance=" + balance +
                ", ownerId=" + ownerId +
                ", cardList=" + cardList +
                '}';
    }

    public String toFile(){
        return IBAN +
                "," + balance +
                "," + ownerId;
    }

}
