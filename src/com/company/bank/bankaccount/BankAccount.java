package com.company.bank.bankaccount;

import com.company.bank.card.Card;
import com.company.bank.transactions.Transaction;
import com.company.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankAccount{
    protected String IBAN;
    protected double balance;
    protected User user;

    protected List<Card> cardList = new ArrayList<>();
    protected List<Transaction> transactions = new ArrayList<>();

    public BankAccount(){
    }

    public BankAccount(User user){
        this.user = user;
        this.IBAN = randomIBANGenerator();
        this.balance = 0;
    }

    public BankAccount(double balance, User user){
        this.IBAN = randomIBANGenerator();
        this.balance = balance;
        this.user = user;
        Card card = new Card();
        this.cardList.add(card);
    }

    public BankAccount(double balance, User user, ArrayList<Card> cardList){
        this.IBAN = randomIBANGenerator();
        this.balance = balance;
        this.user = user;
        this.cardList = cardList;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public User getUser() {
        return user;
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

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void showAccount(){
        System.out.println("Account Holder Name: " + this.user.getFirstName() + " " + this.user.getLastName());
        System.out.println("Account Number: " + this.getIBAN());
        System.out.println("Account Balance: " + this.getBalance());
    }
}
