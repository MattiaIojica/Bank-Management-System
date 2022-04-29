package com.company;

public class SavingsAccount extends BankAccount {

    SavingsAccount(User user){
        super(user); //refer to immediate parent
        setWithdrawLimits(10, 10000);
        setBalance(1000);
    }

    SavingsAccount(String number, String pin, double balance, User user) {
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
        return SAVINGS;
    }
}
