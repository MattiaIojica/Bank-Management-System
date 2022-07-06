package com.company.bank.bankaccount;

public class BankAccountFactory {

    private static BankAccountFactory instance;

    public BankAccountFactory(){}

    public static BankAccountFactory getInstance() {
        if(instance == null){
            instance = new BankAccountFactory();
        }
        return instance;
    }

    public BankAccount createAccount(int customerId){
        return new BankAccount(customerId);
    }
}
