package com.company;

import java.util.*;

public class User {
    String firstName;
    String lastName;
    String id; //CNP
    String email;
    String phoneNo;
    String sex;

    public User(String firstName, String lastName, String id,
                String email, String phoneNo, String sex){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.email = email;
        this.phoneNo = phoneNo;
        this.sex = sex;
    }

    static final Scanner cin = new Scanner(System.in);

    public User(){
        System.out.print("First Name: ");
        this.firstName = cin.nextLine();
        System.out.print("Last Name: ");
        this.lastName = cin.nextLine();
        System.out.print("Id: ");
        this.id = cin.nextLine();
        System.out.print("Email: ");
        this.email = cin.nextLine();
        System.out.print("Phone Number: ");
        this.phoneNo = cin.nextLine();
        System.out.print("Sex: ");
        this.sex = cin.nextLine();
    }

    public String toString()
    {
        return  firstName + "\n" + lastName + "\n" + id + '\n' + email + "\n"
                + phoneNo + "\n" + sex;
    }
}