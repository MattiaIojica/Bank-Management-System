package com.company.bank.bankaccount;

import com.company.person.user.UserSingleton;

import java.util.ArrayList;
import java.util.List;

public class BankAccountSingleton {

    private static BankAccountSingleton instance;

    public BankAccountSingleton(){}

    public static BankAccountSingleton getInstance() {
        if(instance == null){
            instance = new BankAccountSingleton();
        }
        return instance;
    }

    public BankAccount createAccount(int customerId){
        return new BankAccount(customerId);
    }
}
