package com.company.bank.transactions;

import java.util.ArrayList;
import java.util.List;

public class ServiceTransaction {

    private List<Transaction> transactions = new ArrayList<>();
    private static ServiceTransaction instance;

    public ServiceTransaction(){
    }

    public static ServiceTransaction getInstance() {
        if(instance == null)
            instance = new ServiceTransaction();
        return instance;
    }

    public void toString(Transaction transaction){
        System.out.println("From: " + transaction.getFrom());
        System.out.println("To: " + transaction.getTo());
        System.out.println("Amount: " + transaction.getAmount());
        System.out.println("Description: " + transaction.getDescriprion());
        System.out.println("Date: " + transaction.getDate());
    }
}
