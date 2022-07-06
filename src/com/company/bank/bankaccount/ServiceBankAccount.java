package com.company.bank.bankaccount;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceBankAccount {

    private List<BankAccount> bankAccounts = new ArrayList<>();
    private static BankAccountFactory instance;


    public static BankAccountFactory getInstance() {
        if (instance == null)
            instance = new BankAccountFactory();
        return instance;
    }


    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
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
            System.out.println("No bank accounts found!");
        }

        return str;
    }

    public void loadFromFile(){

        List<String[]> str = ServiceBankAccount.getDataFromFile("data/bankAccounts.csv");

        for(String [] ar : str){
            BankAccount bankAccount = new BankAccount(
                    ar[0],
                    Double.parseDouble(ar[1]),
                    Integer.parseInt(ar[2])
            );
            bankAccounts.add(bankAccount );
        }
    }

    public void uploadToFile(){
        try {
            Writer writer = new FileWriter("data/bankAccounts.csv");
            for(BankAccount bankAccount : this.bankAccounts){
                writer.write(bankAccount.toFile());
                writer.write('\n');
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

}
