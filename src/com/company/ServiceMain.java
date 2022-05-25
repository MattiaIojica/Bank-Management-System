package com.company;

import com.company.bank.bankaccount.BankAccount;
import com.company.bank.bankaccount.BankAccountSingleton;
import com.company.bank.transactions.Transaction;
import com.company.databases.BankAccountDatabase;
import com.company.databases.TransactionDatabase;
import com.company.databases.UserDatabase;
import com.company.user.User;
import com.company.user.UserSingleton;
import java.text.ParseException;
import java.util.*;

public class ServiceMain {

    private List<User> users = new ArrayList<>();
    private List<BankAccount> bankAccounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private final UserSingleton userSingleton = new UserSingleton();
    private final BankAccountSingleton bankAccountSingleton = new BankAccountSingleton();
    private UserDatabase userDatabase = null;
    private BankAccountDatabase bankAccountDatabase = null;
    private TransactionDatabase transactionDatabase = null;
    private final Map<String, BankAccount> map = new HashMap<>();

    public ServiceMain(){

    }

    public ServiceMain(UserDatabase userDatabase, BankAccountDatabase bankAccountDatabase, TransactionDatabase transactionDatabase){
        this.userDatabase = userDatabase;
        this.bankAccountDatabase = bankAccountDatabase;
        this.transactionDatabase = transactionDatabase;
        this.users = userDatabase.read();
        this.bankAccounts = bankAccountDatabase.read();
        this.transactions = transactionDatabase.read();

        this.mapBankAccounts();
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    private void mapBankAccounts(){
        for(BankAccount bankAccount : this.bankAccounts)
        {
            this.map.put(bankAccount.getIBAN(), bankAccount);
        }
    }

    private List<BankAccount> getUserBankAccounts(User user){
        List<BankAccount> userBankAccounts = new ArrayList<BankAccount>();

        for(BankAccount b : bankAccounts)
            if(b.getOwnerId() == user.getId())
                userBankAccounts.add(b);

        return userBankAccounts;
    }


    private User getUserById(Scanner cin) throws Exception{
        if(this.users.size() == 0)
            throw new Exception("No users into the database!");
        if(this.users.size() == 1)
            return users.get(0);

        System.out.println("Number of users: " + this.users.size());

        int id = Integer.parseInt(cin.nextLine());
        return users.get(id);
    }

    private BankAccount getBankAccountFromInput(Scanner cin, User user) throws Exception {
        List<BankAccount> userBankAccounts = getUserBankAccounts(user);

        System.out.println("Customer accounts: " + userBankAccounts);
        System.out.println("Insert IBAN: ");
        String IBAN = cin.nextLine();
        if(!this.map.containsKey(IBAN))
            throw new Exception("Invalid IBAN number!");

        BankAccount b = map.get(IBAN);
        if(b.getOwnerId() != user.getId())
            throw new Exception("This user is not the owner of the account with this IBAN!");
        return b;
    }

    public void createUser(Scanner cin) throws ParseException{
        User user = userSingleton.createUser(cin);
        this.users.add(user);

        if(this.userDatabase != null)
            this.userDatabase.create(user);
        System.out.println("User successfully created!");
    }

    public void printUser(Scanner cin) throws Exception {
        User user = this.getUserById(cin);
        System.out.println(user.toString());
    }

    public void createBankAccount(Scanner cin) throws Exception{
         User user = this.getUserById(cin);
         BankAccount bankAccount = this.bankAccountSingleton.createAccount(user.getId());
         bankAccounts.add(bankAccount);
         map.put(bankAccount.getIBAN(), bankAccount);

         if(this.bankAccountDatabase != null)
             this.bankAccountDatabase.create(bankAccount);
         System.out.println("Bank account successfully created!");
    }

    public void closeBankAccount(Scanner cin) throws Exception {
        User user = this.getUserById(cin);
        BankAccount bankAccount = this.getBankAccountFromInput(cin, user);
        List<BankAccount> userBankAccounts = getUserBankAccounts(user);

        if(userBankAccounts.size() == 0)
            throw new Exception("This user has no bank accounts!");

        this.map.remove(bankAccount.getIBAN());
        this.bankAccounts.remove(bankAccount);
        if(this.bankAccountDatabase != null)
            this.bankAccountDatabase.delete(bankAccount);
        System.out.println("Bank Account successfully closed!");
    }

    public void deleteUser(Scanner cin) throws Exception{
        User user = this.getUserById(cin);

        List<BankAccount> userBankAccounts = getUserBankAccounts(user);


        for(BankAccount bankAccount : userBankAccounts){
            this.map.remove(bankAccount.getIBAN());
            this.bankAccounts.remove(bankAccount);
            if(this.bankAccountDatabase != null)
                this.bankAccountDatabase.delete(bankAccount);
        }

        this.users.remove(user);
        if(this.userDatabase != null)
            this.userDatabase.delete(user);

        System.out.println("User successfully deleted!");
    }

    public void getUserBankAccounts(Scanner cin) throws Exception {
        User user = getUserById(cin);
        List<BankAccount> userBankAccounts = getUserBankAccounts(user);

        System.out.println(userBankAccounts.toString());
    }

    public void createTransaction(Scanner cin) throws Exception{
        System.out.println("Sender (IBAN): ");
        String from = cin.nextLine();
        System.out.println("Receiver (IBAN): ");
        String to = cin.nextLine();
        System.out.println("Amount: ");
        double amount = Double.parseDouble(cin.nextLine());
        System.out.println("Description: ");
        String description = cin.nextLine();

        if(from.equals(to))
            throw new Exception("Please use different IBAN's");

        BankAccount sender = null;
        BankAccount receiver = null;

        if(map.containsKey(from))
            sender = map.get(from);

        if(map.containsKey(to))
            receiver = map.get(to);

        if(sender == null || receiver == null)
            throw new Exception("Wrong IBAN used!");

        if(sender.getBalance() < amount)
            throw new Exception("Insufficient founds!");

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        Transaction transaction = new Transaction(from, to, amount, description);
        this.transactions.add(transaction);

//        System.out.println(transaction.toString());

        if(this.transactionDatabase != null)
            this.transactionDatabase.create(transaction);

        if(this.bankAccountDatabase != null)
        {
            this.bankAccountDatabase.update(sender);
            this.bankAccountDatabase.update(receiver);
        }

        System.out.println("Transaction successfully done!");
    }

    public void createCard(Scanner cin) throws Exception {
        User user = this.getUserById(cin);
        BankAccount bankAccount = this.getBankAccountFromInput(cin, user);

        bankAccount.addCard();
    }

    public void getUserTransactions(Scanner cin) throws Exception{
        User user = this.getUserById(cin);
        List<BankAccount> userBankAccounts = getUserBankAccounts(user);

        for(BankAccount b : userBankAccounts)
            for(Transaction t : transactions){
                if(t.getFrom().equals(b.getIBAN()))
                    System.out.println(t.toString());
            }
    }

    public void depositMoney(Scanner cin) throws Exception{
        User user = this.getUserById(cin);
        List<BankAccount> userBankAccounts = this.getUserBankAccounts(user);

        String IBAN;
        int amount = Integer.parseInt(cin.nextLine());

        if(userBankAccounts.size() == 0)
            throw new Exception("This user has no Bank Accounts!");
        if(userBankAccounts.size() == 1)
        {
            userBankAccounts.get(0).setBalance(userBankAccounts.get(0).getBalance() + amount);
            IBAN = userBankAccounts.get(0).getIBAN();
            if(this.bankAccountDatabase!=null){
                this.bankAccountDatabase.update(userBankAccounts.get(0));
            }
        }
        else
        {
            IBAN = cin.nextLine();
            boolean ok = false;
            int idx = -1;
            for(BankAccount b : userBankAccounts){
                if(IBAN == b.getIBAN())
                {
                    ok = true;
                    idx++;
                    break;
                }
            }

            if(ok == true){
                userBankAccounts.get(idx).setBalance(userBankAccounts.get(idx).getBalance() + amount);
                if(this.bankAccountDatabase!=null){
                    this.bankAccountDatabase.update(userBankAccounts.get(idx));
                }
            }
        }

        System.out.println("Deposit successfully done!");

    }

    public void withdrawMoney(Scanner cin) throws Exception{
        User user = this.getUserById(cin);
        List<BankAccount> userBankAccounts = this.getUserBankAccounts(user);

        String IBAN;
        int amount = 0;


        if(userBankAccounts.size() == 0)
            throw new Exception("This user has no Bank Accounts!");
        if(userBankAccounts.size() == 1)
        {
            amount = Integer.parseInt(cin.nextLine());
            if(amount > userBankAccounts.get(0).getBalance())
                throw new Exception("Insufficient founds!");

            userBankAccounts.get(0).setBalance(userBankAccounts.get(0).getBalance() - amount);
            IBAN = userBankAccounts.get(0).getIBAN();
            if(this.bankAccountDatabase!=null){
                this.bankAccountDatabase.update(userBankAccounts.get(0));
            }
        }
        else
        {
            IBAN = cin.nextLine();
            boolean ok = false;
            int idx = -1;
            for(BankAccount b : userBankAccounts){
                if(IBAN == b.getIBAN())
                {
                    ok = true;
                    idx++;
                    break;
                }
            }

            if(ok == true){
                amount = Integer.parseInt(cin.nextLine());
                if(amount > userBankAccounts.get(idx).getBalance())
                    throw new Exception("Insufficient founds!");
                userBankAccounts.get(idx).setBalance(userBankAccounts.get(idx).getBalance() - amount);
                if(this.bankAccountDatabase!=null){
                    this.bankAccountDatabase.update(userBankAccounts.get(idx));
                }
            }
        }

        System.out.println("Withdraw successfully done!");

    }

    public void checkBalance(Scanner cin) throws Exception {
        User user = this.getUserById(cin);
        List<BankAccount> userBankAccounts = this.getUserBankAccounts(user);

        if (userBankAccounts.size() == 0)
            throw new Exception("This user has no Bank Accounts!");
        if (userBankAccounts.size() == 1) {
            System.out.println(userBankAccounts.get(0).getBalance());
        } else {
            int total = 0;
            for (int i = 0; i < userBankAccounts.size(); i++) {
                System.out.println(userBankAccounts.get(0).getIBAN() + ": " + userBankAccounts.get(i).getBalance());
                total += userBankAccounts.get(i).getBalance();
            }

            System.out.println("Total balance: " + total);
        }
    }

}
