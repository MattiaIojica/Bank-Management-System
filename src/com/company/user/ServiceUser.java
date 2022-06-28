package com.company.user;

import com.company.bank.bankaccount.BankAccount;
import com.company.bank.bankaccount.BankAccountSingleton;
import com.company.bank.bankaccount.ServiceBankAccount;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceUser {

    private List<User> users = new ArrayList<>();
    private static ServiceUser instance;


    public static ServiceUser getInstance() {
        if (instance == null)
            instance = new ServiceUser();
        return instance;
    }

    public void setUser(List<User> users){
        this.users = users;
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
            System.out.println("No users found!");
        }

        return str;
    }

    public void loadFromFile(){

        try {
            List<String[]> str = ServiceUser.getDataFromFile("data/users.csv");

            for (String[] ar : str) {
                User user = new User(
                        Integer.parseInt(ar[0]),
                        ar[1],
                        ar[2],
                        ar[3],
                        ar[4],
                        ar[5],
                        new SimpleDateFormat("yyyy-MM-dd").parse(ar[6]),
                        ar[7]
                );
                users.add(user);
            }
            UserSingleton.incrementId(str.size());
        }catch (ParseException e){
            System.out.println(e.toString());
        }
    }

    public void uploadToFile(){
        try {
            Writer writer = new FileWriter("data/users.csv");
            for(User user : this.users){
                writer.write(user.toFile());
                writer.write('\n');
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
