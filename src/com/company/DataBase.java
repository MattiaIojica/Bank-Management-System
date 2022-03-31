package com.company;

import java.io.*;
import java.util.*;

public class DataBase {
    private static DataBase instance;
    ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();


    public static DataBase getInstance(){
        if(instance == null){
            instance = new DataBase();
        }
        return instance;
    }

    BankAccount getBankAccount(String number){
        for(int i = 0; i < bankAccounts.size(); i++){
            if(bankAccounts.get(i).getAccNo().equals(number))
                return bankAccounts.get(i);
        }
        return null;
    }

    BankAccount getBankAccount(String number, String pin){
        for(int i = 0; i < bankAccounts.size(); i++){
            if(bankAccounts.get(i).getAccNo().equals(number) && bankAccounts.get(i).getPin().equals(pin))
                return bankAccounts.get(i);
        }
        return null;
    }

    public void addNewBankAccount(BankAccount acc){
        this.bankAccounts.add(acc);
//        updateData(acc, "BankAccountsList.txt");
    }

    boolean isUnique(String number){
        for(int i = 0; i < bankAccounts.size(); i++)
        {
            if(bankAccounts.get(i).getAccNo().equals(number))
                return false;
        }
        return true;
    }

    //trying to add every new bank account into the file representing the database
//    void updateData(BankAccount acc, String filename){
//        try{
//            FileWriter fileWriter = new FileWriter(filename, true);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//
//            bufferedWriter.newLine();
//            bufferedWriter.write(acc.getBankAccountType());
//            bufferedWriter.newLine();
//            bufferedWriter.write(acc.getAccNo());
//            bufferedWriter.newLine();
//            bufferedWriter.write(acc.getPin());
//            bufferedWriter.newLine();
//            bufferedWriter.write(acc.user.firstName);
//            bufferedWriter.newLine();
//            bufferedWriter.write(acc.user.lastName);
//            bufferedWriter.newLine();
//            bufferedWriter.write(acc.user.id);
//            bufferedWriter.newLine();
//            bufferedWriter.write(acc.user.email);
//            bufferedWriter.newLine();
//            bufferedWriter.write(acc.user.phoneNo);
//            bufferedWriter.newLine();
//            bufferedWriter.write(acc.user.sex);
//
//            bufferedWriter.close();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    void printBankAccounts(){
        for(int i = 0; i < bankAccounts.size(); i++){
            bankAccounts.get(i).show_account();
//            System.out.println(bankAccounts.get(i).getAccNo() + " " + bankAccounts.get(i).getPin());
            System.out.println();
        }
    }

    void loadDataFromFile(String filename){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)));
            String bankAccountType;

            while ((bankAccountType = bufferedReader.readLine()) != null){
                BankAccount bankAccount;

                if(bankAccountType.equals(String.valueOf(BankAccount.STUDENT))){
                    bankAccount = new StudentAccount(bufferedReader.readLine(), bufferedReader.readLine(),
                            Double.parseDouble(bufferedReader.readLine()),
                            new User(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(),
                                    bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine()));
//                    bankAccount.activated = Boolean.getBoolean(bufferedReader.readLine());
                }
                else
                if(bankAccountType.equals(String.valueOf(BankAccount.SAVINGS))){
                    bankAccount = new SavingsAccount(bufferedReader.readLine(), bufferedReader.readLine(),
                            Double.parseDouble(bufferedReader.readLine()),
                            new User(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(),
                                    bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine()));
//                    bankAccount.activated = Boolean.getBoolean(bufferedReader.readLine());
                }
                else
                if(bankAccountType.equals(String.valueOf(BankAccount.CURRENT))){
                    bankAccount = new CurrentAccount(bufferedReader.readLine(), bufferedReader.readLine(),
                            Double.parseDouble(bufferedReader.readLine()),
                            new User(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(),
                                    bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine()));
//                    bankAccount.activated = Boolean.getBoolean(bufferedReader.readLine());
                }

            }

            bufferedReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
}
