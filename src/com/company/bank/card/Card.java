package com.company.bank.card;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Card {
    protected String number;
    protected String pin;
    protected String cvv;

    public Card(){
        this.number = randomNumberGenerator();
        this.pin = randomPinGenerator();
        this.cvv = randomCvvGenerator();
    }

    public Card(String pin){
        this.number = randomNumberGenerator();
        this.pin = pin;
        this.cvv = randomCvvGenerator();
    }

    public Card(String number, String pin, String cvv){
        this.number = number;
        this.pin = pin;
        this.cvv = cvv;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }


    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public String getCvv() {
        return cvv;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(number, card.number) && Objects.equals(pin, card.pin) && Objects.equals(cvv, card.cvv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, pin);
    }
}
