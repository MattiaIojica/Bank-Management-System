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


    public Transaction(String from, String to, double amount, String descriprion) throws Exception {

        if(amount <= 0)
            throw new Exception("The amount cannot be a negative number");

        this.from = from;
        this.to = to;
        this.amount = amount;
        this.description = descriprion;
        this.date = new Date();
    }

    public Transaction(String from, String to, double amount, String descriprion, Date date) throws Exception {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.description = descriprion;
        this.date = date;
    }

    public Transaction(ResultSet in) throws SQLException {
        this.from = in.getString("from");
        this.to = in.getString("to");
        this.amount = in.getDouble("amount");
        this.description = in.getString("description");
        this.date = in.getDate("date");
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
                "," + (new SimpleDateFormat("yyyy-MM-dd h:m:s")).format(date);
    }
}
