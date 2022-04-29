package com.company.user;

import com.company.bank.bankaccount.BankAccount;

import java.util.*;

public class User {
    protected String firstName;
    protected String lastName;
    protected String id; //CNP
    protected String email;
    protected String phoneNo;
    protected String sex;
    protected List<BankAccount> bankAccounts = new ArrayList<>();

    public User(){
    }

    public User(String firstName, String lastName, String id,
                String email, String phoneNo, String sex){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.email = email;
        this.phoneNo = phoneNo;
        this.sex = sex;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getSex() {
        return sex;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(phoneNo, user.phoneNo) && Objects.equals(sex, user.sex) && Objects.equals(bankAccounts, user.bankAccounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id, email, phoneNo, sex, bankAccounts);
    }

    public void showUser()
    {
        System.out.println("User information:");
        System.out.println("Name: " + this.getFirstName() + " " + this.getLastName());
        System.out.println("Id: " + this.getId());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Phone Number: " + this.getPhoneNo());
        System.out.println("Sex: " + this.getSex());
    }
}