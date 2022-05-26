package com.company.bank.card;


import com.company.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class CardSingleton {
    private static int id = 0;

    public Card addCard(String IBAN){
        return new Card(id++, IBAN);
    }

    public Card createCard(Scanner cin) throws ParseException {
        return new Card(id++, String.valueOf(cin));
    }

    public Card createCard(ResultSet cin) throws SQLException {
        return new Card(id++, cin);
    }


}
