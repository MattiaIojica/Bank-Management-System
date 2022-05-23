package com.company.bank.bankaccount;

import java.util.ArrayList;
import java.util.List;

public class BankAccountSingleton {

    private static int id = 0;

    public static void incrementId(int number) {
        BankAccountSingleton.id = BankAccountSingleton.id + number;
    }

    public BankAccount createAccount(String name, int customerId){
        return new BankAccount(name, customerId, id++);
    }
}
