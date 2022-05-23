package com.company.bank.bankaccount;

import com.company.bank.card.Card;
import com.company.bank.card.CardSingleton;
import com.company.bank.transactions.Transaction;
import com.company.user.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BankAccount implements  Comparator<Transaction> {
    protected String IBAN;
    protected double balance;
    protected int ownerId;

    protected List<Card> cardList = new ArrayList<>();
    private final CardSingleton cardSingleton = new CardSingleton();
    static private final Set<String> usedNumbers = new HashSet<>();

    public BankAccount(String IBAN, double balance, int ownerId){
        this.IBAN = IBAN;
        this.balance = balance;
        this.ownerId = ownerId;
    }

    public BankAccount(String name, int ownerId) {
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


    public void addCard(){
        Card card = cardSingleton.addCard(this.IBAN);
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

    public List<Transaction> transactionFilter(List<Transaction> transactions){
        List<Transaction> transactionList = new ArrayList<>();

        for(Transaction t : transactions){
            if(t.getFrom().equals(this.IBAN)){
                transactionList.add(t);
            }
        }

        return transactionList;
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

    @Override
    public int compare(Transaction o1, Transaction o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
