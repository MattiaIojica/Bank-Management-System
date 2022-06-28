package com.company.databases;

import com.company.bank.bankaccount.BankAccount;

import java.util.*;
import java.sql.*;

public class BankAccountDatabase {
    Connection connection;

    public BankAccountDatabase(Connection connection){
        this.connection = connection;
    }

    public void create(BankAccount bankAccount){
        try{
            String query = "INSERT INTO BankAccounts (IBAN, balance, ownerId) VALUES (?, ?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, bankAccount.getIBAN());
            prepareStatement.setDouble(2, bankAccount.getBalance());
            prepareStatement.setInt(3, bankAccount.getOwnerId());
            prepareStatement.execute();
            prepareStatement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public List<BankAccount> read(){
        List<BankAccount> bankAccounts = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM BankAccounts");
            while(result.next()) {
                BankAccount bankAccount = new BankAccount(result);
                bankAccounts.add(bankAccount);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return bankAccounts;
    }

    public void update(BankAccount bankAccount){
        try{
            String query = "UPDATE BankAccounts SET balance = ?, ownerId = ? WHERE IBAN = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setDouble(1, bankAccount.getBalance());
            prepareStatement.setInt(2, bankAccount.getOwnerId());
            prepareStatement.setString(3, bankAccount.getIBAN());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }


    public void delete(BankAccount bankAccount){
        try{
            String query = "DELETE FROM BankAccounts WHERE IBAN = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, bankAccount.getIBAN());
            prepareStatement.execute();
            prepareStatement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
