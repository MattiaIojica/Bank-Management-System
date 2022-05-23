package com.company.user;

import com.company.bank.bankaccount.BankAccount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    private final int id;
    private String firstName;
    private String lastName;
    private String CNP; //CNP
    private String email;
    private String phoneNo;
    private Date birthDate;
    private String sex;

//    protected List<BankAccount> bankAccounts = new ArrayList<>();

    public User(int id, String firstName, String lastName, String CNP,
                String email, String phoneNo, Date birthDate, String sex){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.email = email;
        this.phoneNo = phoneNo;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public User(int id, Scanner in) throws ParseException{
        this.id = id;
        this.readUser(in);
    }

    public User(int id, ResultSet in) throws SQLException {
        this.id = id;
        this.readUser(in);
    }

    public void readUser(ResultSet in) throws SQLException {
        this.firstName = in.getString("firstName");
        this.lastName = in.getString("lastName");
        this.CNP = in.getString("CNP");
        this.email = in.getString("email");
        this.phoneNo = in.getString("phoneNo");
        this.birthDate = in.getDate("birthDate");
        this.sex = in.getString("sex");
    }

    public void readUser(Scanner in) throws ParseException {
        System.out.println("First name: ");
        this.firstName = in.nextLine();
        System.out.println("Last name: ");
        this.lastName = in.nextLine();
        System.out.println("CNP: ");
        this.CNP = in.nextLine();
        System.out.println("Email: ");
        this.email = in.nextLine();
        System.out.println("Phone: ");
        this.phoneNo = in.nextLine();
        System.out.println("Birth Date (yyyy-MM-dd): ");
        this.birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(in.nextLine());
        System.out.println("Sex: ");
        this.sex = in.nextLine();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCNP() {
        return CNP;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", CNP='" + CNP + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", birthDate=" + birthDate +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String toFile() {
        return id +
                "," + firstName +
                "," + lastName +
                "," + CNP +
                "," + email +
                "," + phoneNo +
                "," + (new SimpleDateFormat("yyyy-MM-dd")).format(birthDate) +
                "," + sex;
    }
}