package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Main {

    static List<String> commands = Arrays.asList("Create a new user", "Create a bank account","Create a new card", "Print user details", "Balance check",
            "Show bank accounts", "Load user", "New transaction", "Show transactions", "Close bank account", "Stop!");

    public static Connection getConnection() {
        try{
            String url = "jdbc:mysql://localhost:3306/projectpao";
            String user = "root";
            String password = "rqbJVALFjsfeaKAp";

            return DriverManager.getConnection(url, user, password);
        }catch (SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }

    private static void printAllCommands(){

            System.out.println("Commands:");
        for(int i = 0; i < commands.size(); i++)
            System.out.println((i+1) + ". " + commands.get(i));
    }

    public static void main(String[] args) {
        printAllCommands();


    }

}