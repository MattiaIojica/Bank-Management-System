package com.company.databases;

import com.company.bank.bankaccount.BankAccount;
import com.company.bank.card.Card;
import com.company.bank.card.CardFactory;
import com.company.config.DatabaseConfig;
import com.company.person.Person;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CardDatabase {

    private static CardDatabase instance;
    private static CardFactory cardFactory = CardFactory.getInstance();
    private static Connection connection = DatabaseConfig.getDatabaseConnection();


    private CardDatabase(){
        create();
    }

    public static CardDatabase getInstance() {
        if(instance == null){
            instance = new CardDatabase();
        }
        return instance;
    }

    public void create(){

        String createSQL = "CREATE TABLE IF NOT EXISTS Cards " +
                "(cardId int NOT NULL,"+
                "cvv varchar(5) DEFAULT NULL,"+
                "number varchar(25) DEFAULT NULL,"+
                "IBAN varchar(25) DEFAULT NULL,"+
                "expirationDate date DEFAULT NULL,"+
                "PRIMARY KEY (cardId))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Card getCardByCardId(int id) {
        String selectSql = "SELECT * FROM Cards WHERE cardId=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCard(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Card card){
        String insertSQL = "INSERT INTO Cards (cardId, cvv, number, IBAN, expirationDate) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement prepareStatement = connection.prepareStatement(insertSQL)){
            prepareStatement.setInt(1, card.getCardId());
            prepareStatement.setString(2, card.getCvv());
            prepareStatement.setString(3, card.getNumber());
            prepareStatement.setString(4, card.getIBAN());
            prepareStatement.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(card.getExpirationDate()));
            prepareStatement.execute();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public List<Card> read(){
        String readSQL = "SELECT * FROM Cards";
        List<Card> cards = new ArrayList<>();
//        BankAccountDatabase bankAccountDatabase = BankAccountDatabase.getInstance();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(readSQL);
            while(result.next()) {
                Card card = cardFactory.createCard(result);
                cards.add(card);

            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return cards;
    }

    //no update method for card


    public void delete(Card card){
        String deleteSQL = "DELETE FROM Cards WHERE cardId = ?";
        try(PreparedStatement prepareStatement = connection.prepareStatement(deleteSQL)){
            prepareStatement.setString(1, card.getNumber());
            prepareStatement.execute();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private Card mapToCard(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Card(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5));
        }
        return null;
    }
}
