package com.company.databases;

import com.company.bank.bankaccount.BankAccount;

import java.util.*;
import java.sql.*;

public class BankAccountDatabase {
    Connection connection;

    public BankAccountDatabase(Connection connection){
        this.connection = connection;
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
            String query = "UPDATE Accounts SET balance = ?, ownerId = ? WHERE IBAN = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, bankAccount.getBalance());
            preparedStmt.setInt(2, bankAccount.getOwnerId());
            preparedStmt.setString(3, bankAccount.getIBAN());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void create(BankAccount bankAccount){
        try{
            String query = "INSERT INTO Accounts (IBAN, balance, ownerId) VALUES (?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, bankAccount.getIBAN());
            preparedStmt.setDouble(2, bankAccount.getBalance());
            preparedStmt.setInt(3, bankAccount.getOwnerId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(BankAccount bankAccount){
        try{
            String query = "DELETE FROM BankAccounts WHERE IBAN = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, bankAccount.getIBAN());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
