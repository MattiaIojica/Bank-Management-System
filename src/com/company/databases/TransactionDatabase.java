package com.company.databases;

import com.company.bank.transactions.Transaction;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TransactionDatabase {

    Connection connection;

    public TransactionDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Transaction> read(){
        List<Transaction> transactions = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Transactions");
            while(resultSet.next()) {
                Transaction current = new Transaction(resultSet);
                transactions.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return transactions;
    }

    public void update(Transaction newTransaction){
        try{
            String query = "UPDATE Transactions SET description = ? WHERE from = ? AND to = ? AND date = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newTransaction.getDescription());
            preparedStmt.setString(2, newTransaction.getFrom());
            preparedStmt.setString(3, newTransaction.getTo());
            preparedStmt.setString(4, (new SimpleDateFormat("yyyy-MM-dd h:m:s")).format(newTransaction.getDate()));
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void create(Transaction transaction){
        try{
            String query = "INSERT INTO Transactions (from, to, amount, description, date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, transaction.getFrom());
            preparedStmt.setString(2, transaction.getTo());
            preparedStmt.setDouble(3, transaction.getAmount());
            preparedStmt.setString(4, transaction.getDescription());
            preparedStmt.setString(5, (new SimpleDateFormat("yyyy-MM-dd h:m:s")).format(transaction.getDate()));
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(Transaction transaction){
        try{
            String query = "DELETE FROM Transactions WHERE from = ?, to = ?, date = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, transaction.getFrom());
            preparedStmt.setString(2, transaction.getTo());
            preparedStmt.setString(3, (new SimpleDateFormat("yyyy-MM-dd h:m:s")).format(transaction.getDate()));
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
