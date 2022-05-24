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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void update(Transaction transaction){
        try{
            String query = "UPDATE Transactions SET description = ? WHERE from = ? AND to = ? AND date = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, transaction.getDescription());
            prepareStatement.setString(2, transaction.getFrom());
            prepareStatement.setString(3, transaction.getTo());
            prepareStatement.setString(5, (new SimpleDateFormat("yyyy-MM-dd h:m:s")).format(transaction.getDate()));
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Transaction transaction){
        try{

            String query = "INSERT INTO Transactions (from, to, amount, description, date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, transaction.getFrom());
            prepareStatement.setString(2, transaction.getTo());
            prepareStatement.setDouble(3, transaction.getAmount());
            prepareStatement.setString(4, transaction.getDescription());
            prepareStatement.setString(5, (new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")).format(transaction.getDate()));
            prepareStatement.execute();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Transaction transaction){
        try{
            String query = "DELETE FROM Transactions WHERE from = ?, to = ?, date = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, transaction.getFrom());
            prepareStatement.setString(2, transaction.getTo());
            prepareStatement.setString(3, (new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")).format(transaction.getDate()));
            prepareStatement.execute();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
