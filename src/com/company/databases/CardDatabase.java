package com.company.databases;

import com.company.bank.card.Card;
import com.company.bank.card.CardSingleton;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CardDatabase {
    Connection connection;

    CardSingleton cardSingleton = new CardSingleton();

    public CardDatabase(Connection connection) {
        this.connection = connection;
    }

    public void create(Card card){
        try{
            String query = "INSERT INTO Cards (cardId, cvv, number, IBAN, expirationDate) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, card.getCardId());
            prepareStatement.setString(2, card.getCvv());
            prepareStatement.setString(3, card.getNumber());
            prepareStatement.setString(4, card.getIBAN());
            prepareStatement.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(card.getExpirationDate()));
            prepareStatement.execute();
            prepareStatement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public List<Card> read(){
        List<Card> cards = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Cards");
            while(result.next()) {
//                System.out.println(result);
                Card card = cardSingleton.createCard(result);
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
        try{
            String query = "DELETE FROM Cards WHERE cardId = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, card.getNumber());
            prepareStatement.execute();
            prepareStatement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
