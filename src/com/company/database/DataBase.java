package com.company;

import com.company.bank.bankaccount.BankAccount;
//import com.company.bank.CurrentAccount;
//import com.company.bank.SavingsAccount;
import com.company.user.ServiceUser;
import com.company.user.User;

import java.io.*;
import java.util.*;

public class DataBase {
    private static DataBase instance;
//    ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();


    public static DataBase getInstance(){
        if(instance == null){
            instance = new DataBase();
        }
        return instance;
    }

//    BankAccount getBankAccount(String iban){
//        for(int i = 0; i < bankAccounts.size(); i++){
//            if(bankAccounts.get(i).getIBAN().equals(iban))
//                return bankAccounts.get(i);
//        }
//        return null;
//    }


//    public void addNewBankAccount(BankAccount acc){
//        this.bankAccounts.add(acc);
////        updateData(acc, "BankAccountsList.txt");
//    }
//
//    boolean isUnique(String number){
//        for(int i = 0; i < bankAccounts.size(); i++)
//        {
//            if(bankAccounts.get(i).getAccNo().equals(number))
//                return false;
//        }
//        return true;
//    }

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

//    void printBankAccounts(){
//        for(int i = 0; i < bankAccounts.size(); i++){
//            bankAccounts.get(i).show_account();
////            System.out.println(bankAccounts.get(i).getAccNo() + " " + bankAccounts.get(i).getPin());
//            System.out.println();
//        }
//    }

    void loadDataFromFile(){


        try {
            BufferedReader in = new BufferedReader(new FileReader("userData.csv"));
            BufferedReader in2 = new BufferedReader(new FileReader("bankData.csv"));
            BufferedReader in3 = new BufferedReader(new FileReader("cardData.csv"));
            String str;
            Double balance;

            while ((str = in.readLine())!= null) {
                String[] ar = str.split(",");
//                System.out.println(Arrays.toString(ar));
//                System.out.println(Arrays.toString(ar));
                balance = Double.parseDouble(in2.readLine());
//                System.out.println(balance);
                User u = new User(ar[0], ar[1], ar[2], ar[3], ar[4], ar[5]);

            }
            in.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }

//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("userData.csv")));
//            String bankAccountType;
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(COMMA_DELIMITER);
//                records.add(Arrays.asList(values));
//            }
//
//            while ((bankAccountType = bufferedReader.readLine()) != null){
//                BankAccount bankAccount;
//
//                if(bankAccountType.equals(String.valueOf(BankAccount.STUDENT))){
//                    bankAccount = new StudentAccount(bufferedReader.readLine(), bufferedReader.readLine(),
//                            Double.parseDouble(bufferedReader.readLine()),
//                            new User(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(),
//                                    bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine()));
////                    bankAccount.activated = Boolean.getBoolean(bufferedReader.readLine());
//                }
//                else
//                if(bankAccountType.equals(String.valueOf(BankAccount.SAVINGS))){
//                    bankAccount = new SavingsAccount(bufferedReader.readLine(), bufferedReader.readLine(),
//                            Double.parseDouble(bufferedReader.readLine()),
//                            new User(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(),
//                                    bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine()));
////                    bankAccount.activated = Boolean.getBoolean(bufferedReader.readLine());
//                }
//                else
//                if(bankAccountType.equals(String.valueOf(BankAccount.CURRENT))){
//                    bankAccount = new CurrentAccount(bufferedReader.readLine(), bufferedReader.readLine(),
//                            Double.parseDouble(bufferedReader.readLine()),
//                            new User(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(),
//                                    bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine()));
////                    bankAccount.activated = Boolean.getBoolean(bufferedReader.readLine());
//                }
//
//            }

//            bufferedReader.close();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        catch (NumberFormatException e){
//            e.printStackTrace();
//        }
    }
}
