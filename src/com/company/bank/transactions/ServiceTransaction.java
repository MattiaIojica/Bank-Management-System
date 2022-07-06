package com.company.bank.transactions;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ServiceTransaction {

    private List<Transaction> transactions = new ArrayList<>();
    private static ServiceTransaction instance;

    private ServiceTransaction(){}

    public static ServiceTransaction getInstance() {
        if(instance == null)
            instance = new ServiceTransaction();
        return instance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    private static List<String[]> getDataFromFile(String fileName){

        List<String[]> str = new ArrayList<>();

        try(BufferedReader in = new BufferedReader(new FileReader(fileName))) {

            String l;
            while((l = in.readLine()) != null ) {
                String[] ar = l.replaceAll(" ", "").split(",");
                str.add(ar);
            }
        } catch (IOException e) {
            System.out.println("No transaction found!");
        }

        return str;
    }

    public void loadFromFile() {
        try {
            var columns = ServiceTransaction.getDataFromFile("data/transactions.csv");
            for(var fields : columns){
                var newTransaction = new Transaction(
                        fields[0],
                        fields[1],
                        Double.parseDouble(fields[2]),
                        fields[3],
                        new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss").parse(fields[4])
                );
                transactions.add(newTransaction);
            }
        }catch (ParseException e){
            System.out.println("Cannot load transactions!");
        } catch (Exception e) {
            System.out.println("Invalid transaction format");
        }
    }

    public void uploadToFile(){
        try {
            Writer writer = new FileWriter("data/transactions.csv");
            for(Transaction transaction : this.transactions){
                writer.write(transaction.toFile());
                writer.write('\n');
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
