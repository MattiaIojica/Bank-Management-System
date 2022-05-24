package com.company.bank.transactions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private final String from;
    private final String to;
    private final double amount;
    private final String description;
    private final Date date;


    public Transaction(String from, String to, double amount, String description) throws Exception {

        if(amount <= 0)
            throw new Exception("The amount cannot be a negative number");

        this.from = from;
        this.to = to;
        this.amount = amount;
        this.description = description;
        this.date = new Date();
    }

    public Transaction(String from, String to, double amount, String description, Date date) throws Exception {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Transaction(ResultSet cin) throws SQLException {
        this.from = cin.getString("from");
        this.to = cin.getString("to");
        this.amount = cin.getDouble("amount");
        this.description = cin.getString("description");
        this.date = cin.getDate("date");
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + (new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss")).format(date) +
                '}';
    }

    public String toFile() {
        return from +
                "," + to +
                "," + amount +
                "," + description +
                "," + (new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss")).format(date);
    }
}
