package com.company;

import com.company.audit.ServiceAudit;
import com.company.databases.BankAccountDatabase;
import com.company.databases.TransactionDatabase;
import com.company.databases.UserDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class Main {

    static List<String> commands = Arrays.asList("Create new user", "Print user", "Delete user", "Create Bank account",
            "Close Bank Account", "Create card", "Print User's Bank Accounts", "Create Transaction", "Deposit Money",
            "Withdraw Money", "Check Balance", "Stop!");


    public static Connection getConnection() {
        try{
            String url = "jdbc:mysql://localhost:3306/projectpao";
            String user = "root";
            String password = "pao2022rootQ";

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

        Scanner cin = new Scanner(System.in);

        Connection connection = Main.getConnection();
        BankAccountDatabase bankAccountDatabase = new BankAccountDatabase(connection);
        UserDatabase userDatabase = new UserDatabase(connection);
        TransactionDatabase transactionDatabase = new TransactionDatabase(connection);

        ServiceMain serviceMain = new ServiceMain(userDatabase, bankAccountDatabase, transactionDatabase);
        ServiceAudit serviceAudit = new ServiceAudit();

        boolean stop = false;
        while (stop != true) {
            System.out.println("Insert Command: (0 to print the command list)");
            int command = Integer.parseInt(cin.nextLine());

            try {
                switch (command) {
                    case 0 -> printAllCommands();
                    case 1 -> serviceMain.createUser(cin);
                    case 2 -> serviceMain.printUser(cin);
                    case 3 -> serviceMain.deleteUser(cin);
                    case 4 -> serviceMain.createBankAccount(cin);
                    case 5 -> serviceMain.closeBankAccount(cin);
                    case 6 -> serviceMain.createCard(cin);
                    case 7 -> serviceMain.getUserBankAccounts(cin);
                    case 8 -> serviceMain.createTransaction(cin);
                    case 9 -> serviceMain.depositMoney(cin);
                    case 10 -> serviceMain.withdrawMoney(cin);
                    case 11 -> serviceMain.checkBalance(cin);
                    case 12 -> serviceMain.getUserTransactions(cin);

                    case 13 -> stop = true;
                }
                if (command >= 0 && command < 12) {
                    if(command != 0)
                        serviceAudit.appendCommand(commands.get(command - 1));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}