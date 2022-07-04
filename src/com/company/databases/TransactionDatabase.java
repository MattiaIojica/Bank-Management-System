package com.company.databases;

import com.company.bank.transactions.Transaction;
import com.company.config.DatabaseConfig;

import java.sql.*;
import java.util.*;

public class TransactionDatabase {

    private static Connection connection = DatabaseConfig.getDatabaseConnection();
    private static TransactionDatabase instance;


    private TransactionDatabase(){
        create();
    }

    public static TransactionDatabase getInstance() {
        if(instance == null){
            instance = new TransactionDatabase();
        }
        return instance;
    }

    public void create(){

        String createSQL = "CREATE TABLE IF NOT EXISTS transactions " +
                "(fromIBAN varchar(25) DEFAULT NULL," +
                "toIBAN varchar(25) DEFAULT NULL," +
                "amount double(10,2) NOT NULL," +
                "description varchar(105) DEFAULT NULL," +
                "dateT datetime DEFAULT NULL"+
                ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Transaction transaction){
        String insertSQL = "INSERT INTO transactions (fromIBAN, toIBAN, amount, description, dateT) VALUES (?, ?, ?, ?, ?)";

        try{
            java.util.Date d = (java.util.Date) transaction.getDate();
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            java.sql.Timestamp sqlTime = new java.sql.Timestamp(d.getTime());
            PreparedStatement prepareStatement = connection.prepareStatement(insertSQL);
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
        String readSQL = "SELECT * FROM Transactions order by dateT";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readSQL);
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
        String updateSQL = "UPDATE Transactions SET description = ? WHERE fromIBAN = ? AND toIBAN = ? AND dateT = ?";

        try{
            java.util.Date d = (java.util.Date) transaction.getDate();
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            java.sql.Timestamp sqlTime = new java.sql.Timestamp(d.getTime());

            PreparedStatement prepareStatement = connection.prepareStatement(updateSQL);
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
        String deleteSQL = "DELETE FROM Transactions WHERE fromIBAN = ?, toIBAN = ?, dateT = ?";
        try{
            java.util.Date d = (java.util.Date) transaction.getDate();
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            java.sql.Timestamp sqlTime = new java.sql.Timestamp(d.getTime());

            PreparedStatement prepareStatement = connection.prepareStatement(deleteSQL);
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
