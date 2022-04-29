package com.company;

public class StudentAccount extends BankAccount{

    StudentAccount(User user){
        super(user); //refer to immediate parent
        setWithdrawLimits(10, 1000);
        setBalance(100);
    }

    StudentAccount(String number, String pin, double balance, User user){
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
        return BankAccount.STUDENT;
    }
}
