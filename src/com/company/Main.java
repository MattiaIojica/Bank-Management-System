package com.company;

import com.company.DataBase;
import com.company.bank.bankaccount.BankAccount;
import com.company.bank.bankaccount.ServiceBankAccount;
import com.company.bank.card.Card;
import com.company.bank.card.ServiceCard;
import com.company.user.ServiceUser;
import com.company.user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        User u = new User("mattia", "iojica", "1", "sasaa@gmmail", "2041294101", "m");
//        BankAccount b = new BankAccount();
//        ServiceBankAccount serviceBankAccount = new ServiceBankAccount();
//        serviceBankAccount.addBankAccount(b);
//
//        serviceBankAccount.showAccount(b);
//        serviceBankAccount.deposit(b, 500);
//        serviceBankAccount.showAccount(b);
//
//
//        serviceBankAccount.showTransactions(b);

//        DataBase dataBase = DataBase.getInstance();
//
//        dataBase.loadDataFromFile();
        ServiceUser serviceUser = ServiceUser.getInstance();
        serviceUser = readUsersFromFile();

        ServiceCard serviceCard = ServiceCard.getInstance();
        serviceCard = readCardsFromFile();

        ServiceBankAccount serviceBankAccount = ServiceBankAccount.getInstance();
        serviceBankAccount = readBankAccountsFromFile(serviceUser, serviceCard);

//        for(int i = 0; i < serviceUser.getUsers().size(); i++){
//            System.out.println(serviceCard.getCards().get(i).getNumber());
//        }
//
//        for(int i = 0; i < serviceBankAccount.getBankAccounts().size(); i++){
//            serviceBankAccount.getBankAccounts().get(i).showAccount();
//        }


        for(int i = 0; i < serviceUser.getUsers().size(); i++){
            serviceUser.addBankAccountToUser(serviceUser.getUsers().get(i), serviceBankAccount.getBankAccounts().get(i));
        }

//        for(int i = 0; i < serviceBankAccount.getBankAccounts().size(); i++){
//            System.out.println(serviceBankAccount.getBankAccounts().get(i).getCardList().size());
//        }

        for(int i = 0; i < serviceUser.getUsers().size(); i++){
            System.out.println(serviceUser.getUsers().get(i).getBankAccounts().get(0).getCardList().get(0).getNumber());
        }
    }

    private static ServiceUser readUsersFromFile() {

        ServiceUser serviceUser = ServiceUser.getInstance();
        try {
            BufferedReader in = new BufferedReader(new FileReader("userData.csv"));
            String str;
            Double balance;

            while ((str = in.readLine()) != null) {
                String[] ar = str.split(",");
                User u = new User(ar[0], ar[1], ar[2], ar[3], ar[4], ar[5]);
                serviceUser.addUser(u);
            }
            in.close();
        } catch (
                IOException e) {
            System.out.println("File Read Error");
        }

        return serviceUser;
    }

    private static ServiceBankAccount readBankAccountsFromFile(ServiceUser serviceUser, ServiceCard serviceCard) {

        int i = 0;
        ServiceBankAccount serviceBankAccount = new ServiceBankAccount().getInstance();
        try {
            BufferedReader in = new BufferedReader(new FileReader("bankData.csv"));
            String str;

            while ((str = in.readLine()) != null) {
                List<Card>cards = new ArrayList<>();
                cards.add(serviceCard.getCards().get(i));
                double amount = Double.parseDouble(str);
//                System.out.println(Arrays.toString(ar));
                BankAccount bankAccount = new BankAccount(amount, serviceUser.getUsers().get(i), (ArrayList<Card>) cards);
//                serviceBankAccount.addCardToBankAccount(bankAccount, serviceCard.getCards().get(i));
                serviceBankAccount.addBankAccount(bankAccount);
//                System.out.println(serviceBankAccount.getBankAccounts().get(i).getCardList().size());
                i++;
            }
            in.close();
        } catch (
                IOException e) {
            System.out.println("File Read Error");
        }

        return serviceBankAccount;
    }

    private static ServiceCard readCardsFromFile() {

        ServiceCard serviceCard = ServiceCard.getInstance();

        try {
            BufferedReader in = new BufferedReader(new FileReader("cardData.csv"));
            String str;

            while ((str = in.readLine()) != null) {
                String[] ar = str.split(",");
//                System.out.println(Arrays.toString(ar));
                Card card = new Card(ar[0], ar[1], ar[2]);
                serviceCard.addCard(card);
            }
            in.close();
        } catch (
                IOException e) {
            System.out.println("File Read Error");
        }

        return serviceCard;
    }
}