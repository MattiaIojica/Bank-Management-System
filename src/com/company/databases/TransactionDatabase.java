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

            String query = "INSERT INTO transactions (fromIBAN, toIBAN, amount, description, dateT) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, transaction.getFrom());
            prepareStatement.setString(2, transaction.getTo());
            prepareStatement.setDouble(3, transaction.getAmount());
            prepareStatement.setString(4, transaction.getDescription());
            prepareStatement.setDate(5, Date.valueOf((new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")).format(transaction.getDate())));
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
            String query = "UPDATE Transactions SET description = ? WHERE fromIBAN = ? AND toIBAN = ? AND dateT = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, transaction.getDescription());
            prepareStatement.setString(2, transaction.getFrom());
            prepareStatement.setString(3, transaction.getTo());
            prepareStatement.setDate(4, Date.valueOf((new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")).format(transaction.getDate())));
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Transaction transaction){
        try{
            String query = "DELETE FROM Transactions WHERE fromIBAN = ?, toIBAN = ?, dateT = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, transaction.getFrom());
            prepareStatement.setString(2, transaction.getTo());
            prepareStatement.setDate(3, Date.valueOf((new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")).format(transaction.getDate())));
            prepareStatement.execute();
            prepareStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
