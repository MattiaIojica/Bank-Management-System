package com.company.bank.transactions;

import java.util.Date;

public class Transaction {
    protected String from;
    protected String to;
    protected double amount;
    protected String descriprion;
    protected Date date;


    public Transaction(String from, String to, double amount, String descriprion){

        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = new Date();
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescriprion() {
        return descriprion;
    }

    public Date getDate() {
        return date;
    }


}
