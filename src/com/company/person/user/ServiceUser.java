package com.company.person.user;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser implements UserInterface {

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
            UserFactory.incrementId(str.size());
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


    public List<User> getUsers(User user) {
        return users;
    }

    public void deteleUser(User user){
        for(int i = 0;i < this.users.size(); ++i){
            if(this.users.get(i).equals(user)){
                this.users.remove(i);
                break;
            }
        }
    }

    public void addUser(User user){
        this.users.add(user);
    }
}
