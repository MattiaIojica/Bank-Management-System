package com.company.bank.bankaccount;

import java.util.ArrayList;
import java.util.List;

public class BankAccountSingleton {

    public BankAccount createAccount(int customerId){
        return new BankAccount(customerId);
    }
}
