package com.company.databases;

import com.company.bank.bankaccount.BankAccount;
import com.company.config.DatabaseConfig;
import com.company.person.Person;

import java.util.*;
import java.sql.*;

public class BankAccountDatabase {

    private static Connection connection = DatabaseConfig.getDatabaseConnection();

    private static BankAccountDatabase instance;

    private BankAccountDatabase(){
        create();
    }

    public static BankAccountDatabase getInstance(){
        if(instance == null){
            instance = new BankAccountDatabase();
        }
        return instance;
    }

    public void create(){

        String createSQL = "CREATE TABLE IF NOT EXISTS Bankaccounts " +
                "(IBAN varchar(20) NOT NULL," +
                "balance double(10,2) DEFAULT NULL," +
                "ownerId int DEFAULT NULL," +
                " PRIMARY KEY (IBAN)" +
                ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(BankAccount bankAccount){
        String insertSQL = "INSERT INTO BankAccounts (IBAN, balance, ownerId) VALUES (?, ?, ?)";

        try(PreparedStatement prepareStatement = connection.prepareStatement(insertSQL)){
            prepareStatement.setString(1, bankAccount.getIBAN());
            prepareStatement.setDouble(2, bankAccount.getBalance());
            prepareStatement.setInt(3, bankAccount.getOwnerId());
            prepareStatement.execute();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public List<BankAccount> read(){
        List<BankAccount> bankAccounts = new ArrayList<>();
        String readSQL = "SELECT * FROM BankAccounts";
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(readSQL);
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
        String updateSQL = "UPDATE BankAccounts SET balance = ?, ownerId = ? WHERE IBAN = ?";
        try(PreparedStatement prepareStatement = connection.prepareStatement(updateSQL)){
            prepareStatement.setDouble(1, bankAccount.getBalance());
            prepareStatement.setInt(2, bankAccount.getOwnerId());
            prepareStatement.setString(3, bankAccount.getIBAN());
            prepareStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }


    public void delete(BankAccount bankAccount){
        String deleteSQL = "DELETE FROM BankAccounts WHERE IBAN = ?";
        try{
            PreparedStatement prepareStatement = connection.prepareStatement(deleteSQL);
            prepareStatement.setString(1, bankAccount.getIBAN());
            prepareStatement.execute();
            prepareStatement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public BankAccount getBankAccountByIban(String IBAN) {
        String selectSql = "SELECT * FROM BankAccounts WHERE IBAN=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, IBAN);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToBankAccount(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BankAccount mapToBankAccount(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new BankAccount(resultSet.getString(1), resultSet.getDouble(2), resultSet.getInt(3));
        }
        return null;
    }
}
