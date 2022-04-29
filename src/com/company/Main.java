package com.company;

public class Main {

    public static void main(String[] args) {

        /*creating a new Database*/
        DataBase db = DataBase.getInstance();

        /*loading the data from file*/
        db.loadDataFromFile("BankAccountsList.txt");

        /*printing the loaded data*/
//        db.printBankAccounts();

//        System.out.println("---------------------------------------------------------------------");

        /*create new user*/
        User user1 = new User("Mattia", "Iojica", "5111111111111",
                "mattia@gmail.com", "07765165151","M");


        /*returns the data of the user*/
        String string = user1.toString();
        System.out.println(string);

        /*create a student account for the new user which is automatically added into the database*/
        StudentAccount user1_acc = new StudentAccount(BankAccount.randomNumberGenerator(), BankAccount.randomPinGenerator(), 9999, user1);
        db.printBankAccounts();


        User user2 = new User("Oliver", "kann", "51561321684",
                "oliverkann@gmail.com", "034524454","M");
        SavingsAccount user2_acc = new SavingsAccount(user2);
        /*print the data of the new account*/
        user2_acc.show_account();
        System.out.println("---------------------------------------------------------------------");

        /*new user using the data provided in the console*/
//        User newUser = new User();
        /*the users are not saved into the database
        for this we must create an account at out choice*/
//        CurrentAccount newCurrentAccount = new CurrentAccount(newUser);
        /*now the data is stored into the database*/
//        db.printBankAccounts();

        /*ALL THE TRANSACTIONS, DEPOSITS, AND WITHDRAWALS ARE UPDATED INTO THE DATABASE*/

        /*------ DEPOSIT -------*/
        /*we can pass the amount or write it into the console*/

        /*passing the amount*/
        System.out.println("\nAccount Banalce before deposit: " + user1_acc.getBalance());
        user1_acc.deposit(1);
        System.out.println("Account Banalce after deposit: " + user1_acc.getBalance());

        /*writing the amount*/
//        System.out.println("Account Banalce before deposit: " + user1_acc.getBalance());
//        user1_acc.deposit();
//        System.out.println("Account Banalce after deposit: " + user1_acc.getBalance());


        /*------ WITHDRAW -------*/
        /*we can pass the amount or write it into the console*/

        /*passing the amount*/
        System.out.println("\nAccount Banalce before withdraw: " + user2_acc.getBalance());
        user2_acc.withdraw(100);
        System.out.println("Account Banalce after withdraw: " + user2_acc.getBalance());

        /*writing the amount*/
//        System.out.println("\nAccount Banalce before withdraw: " + user2_acc.getBalance());
//        user2_acc.withdraw();
//        System.out.println("Account Banalce after withdraw: " + user2_acc.getBalance());

        /*------ TRANSFER -------*/

        /*we can transfer to an specific user by passing the amount or writing it into the console*/

        /*passing the amount*/
        System.out.println("\nUser1 Balance before: " + user1_acc.getBalance());
        System.out.println("User2 Balance before: " + user2_acc.getBalance());
        user1_acc.transfer(user2_acc, 100);
        System.out.println("\nUser1 Balance after: " + user1_acc.getBalance());
        System.out.println("User2 Balance after: " + user2_acc.getBalance());

        /*writing the amount*/
//        System.out.println("\nUser1 Balance before: " + user1_acc.getBalance());
//        System.out.println("User2 Balance before: " + user2_acc.getBalance());
//        user1_acc.transfer(user2_acc);
//        System.out.println("\nUser1 Balance after: " + user1_acc.getBalance());
//        System.out.println("User2 Balance after: " + user2_acc.getBalance());

        db.printBankAccounts();


        /*LET'S EMPTY ALL THE ACCOUNTS :D */
        for(var back_acc : db.bankAccounts)
            back_acc.withdraw(back_acc.getBalance());

        System.out.println("---------------------------------------------------------------------\n");


        db.printBankAccounts();
    }
}