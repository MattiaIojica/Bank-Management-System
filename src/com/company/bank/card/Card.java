package com.company.bank.card;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Card {
    private final int cardId;
    private final String cvv;
    private String number;
    private String IBAN;
    private final Date expirationDate;

    static private final Set<String> usedNumbers = new HashSet<>();

    public Card(int cardId, String cvv, String number, String IBAN, Date expirationDate) {
        this.cardId = cardId;
        this.cvv = cvv;
        this.number = number;
        this.IBAN = IBAN;
        this.expirationDate = expirationDate;
    }

    public Card(int cardId, String IBAN) {
        this.cardId = cardId;
        this.IBAN = IBAN;
        this.number = randomNumberGenerator();
        this.cvv = randomCvvGenerator();

        //check if the number is already used
        while(usedNumbers.contains(this.number))
            this.number = randomNumberGenerator();
        usedNumbers.add(this.number);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 4);
        this.expirationDate = calendar.getTime();
    }

    public Card(int id, ResultSet in) throws SQLException {
        this.cvv = randomCvvGenerator();
        this.cardId = id;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 4);
        this.expirationDate = calendar.getTime();
        this.readCard(in);
    }

    public void readCard(ResultSet in) throws SQLException {
        this.number = in.getString("number");
        this.IBAN = in.getString("IBAN");
    }

    public void readCard(Scanner in){
        System.out.println("IBAN: ");
        this.IBAN = in.nextLine();
    }

    public int getCardId() {
        return cardId;
    }

    public String getCvv() {
        return cvv;
    }

    public String getNumber() {
        return number;
    }

    public String getIBAN() {
        return IBAN;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    private String randomNumberGenerator(){
        String str = "";
        int min = 1;
        int max = 9;
        Random random = new Random();
        for(int i = 0; i < 16; i++)
            str += random.nextInt(max - min + 1) + min;

        return str;
    }

    static private String randomPinGenerator(){
        Random random = new Random();

        return String.valueOf(random.nextInt(1000) + 8999);
    }

    static private String randomCvvGenerator(){
        Random random = new Random();

        return String.valueOf(random.nextInt(100) + 899);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cvv='" + cvv + '\'' +
                ", number='" + number + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
