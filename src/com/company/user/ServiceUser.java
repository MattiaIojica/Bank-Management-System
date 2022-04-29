package com.company.user;

import com.company.bank.bankaccount.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class ServiceUser {

    private List<User> users = new ArrayList<>();
    private static ServiceUser instance;

    private ServiceUser(){
    }

    public static ServiceUser getInstance() {
        if(instance == null)
            instance = new ServiceUser();
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(String id){

        for (int i = 0; i < this.users.size(); i++)
            if (this.users.get(i).getId().equals(id))
                return this.users.get(i);

        return null;
    }

    public boolean checkEmail(String email){

        for (int i = 0; i < this.users.size(); i++)
            if (this.users.get(i).getEmail().equals(email))
                return true;

        return false;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void removeUser(User user){
        this.users.removeIf(x -> x.getId() == user.getId());
    }

    public void addBankAccountToUser(User user, BankAccount bankAccount){
        List <BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.addAll(user.getBankAccounts());
        bankAccounts.add(bankAccount);
        user.setBankAccounts(bankAccounts);
    }


}
