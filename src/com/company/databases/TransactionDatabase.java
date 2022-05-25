package com.company.databases;

import com.company.bank.transactions.Transaction;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class TransactionDatabase {

    Connection connection;

    public TransactionDatabase(Connection connection) {
        this.connection = connection;
    }

    public void create(Transaction transaction){
        try{
            java.util.Date d = (java.util.Date) transaction.getDate();
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            java.sql.Timestamp sqlTime = new java.sql.Timestamp(d.getTime());
            String query = "INSERT INTO transactions (fromIBAN, toIBAN, amount, description, dateT) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, transaction.getFrom());
            prepareStatement.setString(2, transaction.getTo());
            prepareStatement.setDouble(3, transaction.getAmount());
            prepareStatement.setString(4, transaction.getDescription());
            prepareStatement.setTimestamp(5, sqlTime);
            prepareStatement.execute();
            prepareStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> read(){
        List<Transaction> transactions = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Transactions");
            while(resultSet.next()) {
                Transaction transaction = new Transaction(resultSet);
                transactions.add(transaction);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void update(Transaction transaction){
        try{
            java.util.Date d = (java.util.Date) transaction.getDate();
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            java.sql.Timestamp sqlTime = new java.sql.Timestamp(d.getTime());

            String query = "UPDATE Transactions SET description = ? WHERE fromIBAN = ? AND toIBAN = ? AND dateT = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, transaction.getDescription());
            prepareStatement.setString(2, transaction.getFrom());
            prepareStatement.setString(3, transaction.getTo());
            prepareStatement.setTimestamp(4, sqlTime);
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Transaction transaction){
        try{
            java.util.Date d = (java.util.Date) transaction.getDate();
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            java.sql.Timestamp sqlTime = new java.sql.Timestamp(d.getTime());
            String query = "DELETE FROM Transactions WHERE fromIBAN = ?, toIBAN = ?, dateT = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, transaction.getFrom());
            prepareStatement.setString(2, transaction.getTo());
            prepareStatement.setTimestamp(4, sqlTime);
            prepareStatement.execute();
            prepareStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
