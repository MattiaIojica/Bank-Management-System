package com.company;

public class CurrentAccount extends BankAccount {

    CurrentAccount(User user){
        super(user); //refer to immediate parent
        setWithdrawLimits(10, 5000);
        setBalance(100);
    }

    CurrentAccount(String number, String pin, double balance, User user){
        this(user);
        super.setAccNo(number);
        super.setPin(pin);
        super.setBalance(balance);
    }

    void setWithdrawLimits(double min, double max){
        minWithdrawal = min;
        maxWithdrawal = max;
    }

    int getBankAccountType(){
        return CURRENT;
    }
}
