package com.company.bank.card;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Card {
    private final int cardId;
    private final String cvv;
    private String number;
    private String IBAN;
    private final Date expirationDate;

    static private final Set<String> usedNumbers = new HashSet<>();

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

    static private String randomNumberGenerator(){
        byte[] number = new byte[16];
        new Random().nextBytes(number);
        return new String(number, StandardCharsets.UTF_8);
    }

    static private String randomPinGenerator(){
        Random random = new Random();

        return String.valueOf(random.nextInt(1000) + 8999);
    }

    static private String randomCvvGenerator(){
        Random random = new Random();

        return String.valueOf(random.nextInt(100) + 899);
    }

}
