package com.company;

import java.util.Random;
import java.util.Scanner;

public abstract class BankAccount{
    private String accNo; //account number
    private String pin;
    protected double balance;
    protected User user; //user data
    boolean activated;

    protected double minWithdrawal;
    protected double maxWithdrawal;

    //bank account types
    public static final int STUDENT = 0;
    public static final int SAVINGS = 1;
    public static final int CURRENT = 2;

    BankAccount(User user){
        accNo = randomNumberGenerator();
        pin = randomPinGenerator();
        this.user = user;
        DataBase db = DataBase.getInstance();
        db.addNewBankAccount(this); //add the new Bank account into the database
    }

    abstract int getBankAccountType();
    abstract void setWithdrawLimits(double min, double max);


    //getters and setters

    String getAccNo(){
        return accNo;
    }
    void setAccNo(String accNo){
        this.accNo = accNo;
    }

    String getPin(){
        return pin;
    }
    void setPin(String pin){
        this.pin = pin;
    }

    double getBalance(){
        return balance;
    }
    void setBalance(double balance){
        this.balance = balance;
    }

    void activateAcc(){
        this.activated = true;
    }


    //random unique account number
    static public String randomNumberGenerator()
    {
        Random random = new Random();
        DataBase dataBase = DataBase.getInstance();
        String accountNumber = String.valueOf(random.nextInt(1000000)+899999);


        if(dataBase.isUnique(accountNumber))
        {
            return accountNumber;
        }

        return randomNumberGenerator();
    }

    //random pin generator
    static public String randomPinGenerator(){
        Random random = new Random();

        return String.valueOf(random.nextInt(1000) + 8999);
    }


    void deposit(double amount){
        this.balance += amount;
    }

    static final Scanner cin = new Scanner(System.in);

    void deposit(){
        System.out.println("Amount to deposit: ");
        double amount = cin.nextDouble();

        this.balance += amount;
    }

    boolean transfer(BankAccount bankAccount, double amount){
        if(balance - amount < 0){
            return false;
        }

        this.balance -= amount;
        bankAccount.balance += amount;
        return true;
    }

    void transfer(BankAccount bankAccount){
        double amount;

        do{
            System.out.print("Amount to withdraw: ");
            amount = cin.nextFloat();

            if(amount > balance){
                System.out.println("Your balance is less than " + amount);
                System.out.println("Transaction declined!");

                System.out.println("Do you want to withdrew another amount? [Y/N]");
                Character c = cin.next().charAt(0);
                if(c != 'y' && c != 'Y')
                    break;
            }
            else
            {
                this.balance -= amount;
                bankAccount.balance += amount;

                break;
            }

        }while(true);
    }

    boolean withdraw(double amount){
        if(amount < 0)
            return false;

        if(amount > this.balance)
        {
            System.out.println("Insufficient balance");
            System.out.println("Current balance: " + this.getBalance());
            return false;
        }

        this.balance -= amount;
        return true;
    }

    void withdraw(){
        double amount;

        do{
            System.out.print("Amount to withdraw: ");
            amount = cin.nextFloat();

            if(amount > balance){
                System.out.println("Your balance is less than " + amount);
                System.out.println("Transaction declined!");

                System.out.println("Do you want to withdrew another amount? [Y/N]");
                Character c = cin.next().charAt(0);
                if(c != 'y' && c != 'Y')
                    break;
            }
            else
            {
                balance -= amount;
                System.out.println("Withdraw succeeded!");
                System.out.println("Your balance is now: " + balance);
                break;
            }

        }while(true);
    }

    public void show_account(){
        System.out.println("Account Holder Name: " + this.user.firstName + " " + this.user.lastName);
        System.out.println("Account Number: " + this.accNo);
        System.out.println("Account Type: " + this.getBankAccountType());
        System.out.println("Account Balance: " + this.balance);
    }

}
