package com.company.bank.bankaccount;


import com.company.bank.card.Card;
import com.company.bank.transactions.Transaction;
import com.company.user.User;

import java.util.ArrayList;
import java.util.List;

public class ServiceBankAccount {

    private List<BankAccount> bankAccounts = new ArrayList<>();
    private static ServiceBankAccount instance;


    public ServiceBankAccount(){
    }

    public static ServiceBankAccount getInstance() {
        if(instance == null)
            instance = new ServiceBankAccount();
        return instance;
    }


    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public BankAccount getBankAccountByUser(User user){
        for (int i = 0; i < this.bankAccounts.size(); ++i)
            if (this.bankAccounts.get(i).getUser().equals(user))
                return this.bankAccounts.get(i);

        return null;
    }

    public BankAccount getBankAccountByIBAN(String iban){
        for (int i = 0; i < this.bankAccounts.size(); ++i)
            if (this.bankAccounts.get(i).getIBAN().equals(iban))
                return this.bankAccounts.get(i);

        return null;
    }

    public void addBankAccount(BankAccount bankAccount){
        this.bankAccounts.add(bankAccount);
    }

    public void addCardToBankAccount(BankAccount bankAccount, Card card){
        List<Card> cards = new ArrayList<>();
        cards.addAll(bankAccount.getCardList());
        cards.add(card);
        bankAccount.setCardList(cards);
    }

    public void deposit(BankAccount bankAccount, double amount)
    {
        bankAccount.setBalance(bankAccount.getBalance() + amount);

        List<Transaction> transactions = new ArrayList<>();

        transactions.addAll(bankAccount.getTransactions());
        Transaction t = new Transaction("---", bankAccount.getIBAN(), amount, "Deposit");
        transactions.add(t);
        bankAccount.setTransactions(transactions);
    }

    boolean transfer(BankAccount from, BankAccount to, double amount){
        if(from.getBalance() < amount)
            return false;

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        List<Transaction> transactionsTo = new ArrayList<>();
        List<Transaction> transactionsFrom = new ArrayList<>();

        transactionsTo.addAll(to.getTransactions());
        transactionsFrom.addAll(from.getTransactions());
        Transaction t = new Transaction(from.getIBAN(), to.getIBAN(), amount, "Transfer");
        transactionsTo.add(t);
        transactionsFrom.add(t);
        to.setTransactions(transactionsTo);
        from.setTransactions(transactionsFrom);

        return true;
    }

    public boolean withdraw(BankAccount bankAccount, double amount){
        if (bankAccount.getBalance() < amount)
            return false;

        bankAccount.setBalance(bankAccount.getBalance() - amount);

        List<Transaction> transactions = new ArrayList<>();

        transactions.addAll(bankAccount.getTransactions());
        Transaction t = new Transaction(bankAccount.getIBAN(),"---", amount, "Withdraw");
        transactions.add(t);
        bankAccount.setTransactions(transactions);

        return true;
    }


    public void showAccount(BankAccount bankAccount){
        System.out.println("Account Holder Name: " + bankAccount.getUser().getFirstName() + " " + bankAccount.getUser().getLastName());
        System.out.println("Account IBAN: " + bankAccount.getIBAN());
        System.out.println("Account Balance: " + bankAccount.getBalance());
    }

    public void showCards(BankAccount bankAccount){

    }

    public void showTransactions(BankAccount bankAccount){

        for(int i = 0; i < bankAccount.getTransactions().size(); i++){
                System.out.println("From: " + bankAccount.getTransactions().get(i).getFrom());
                System.out.println("To: " + bankAccount.getTransactions().get(i).getTo());
                System.out.println("Amount: " + bankAccount.getTransactions().get(i).getAmount());
                System.out.println("Description: " + bankAccount.getTransactions().get(i).getDescriprion());
                System.out.println("Date: " + bankAccount.getTransactions().get(i).getDate());
                System.out.println();
        }
    }

}
