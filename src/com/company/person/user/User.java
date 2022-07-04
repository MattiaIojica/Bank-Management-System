package com.company.person.user;

import com.company.person.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class User extends Person {

    private final int id;


    public User(int id, String firstName, String lastName, String CNP,
                  String email, String phoneNo, Date birthDate, String sex){
        super(firstName, lastName, CNP, email, phoneNo, birthDate, sex);
        this.id = id;
    }

    public User(int id, Scanner in) throws ParseException{
        super();
        this.id = id;
        this.readUser(in);
    }

    public User(int id, ResultSet in) throws SQLException {
        super();
        this.id = id;

        this.readUser(in);
    }



    public int getId() {
        return id;
    }

    @Override
    public void setSex(String sex) {
        super.setSex(sex);
    }

    @Override
    public void setPhoneNo(String phoneNo) {
        super.setPhoneNo(phoneNo);
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setBirthDate(Date birthDate) {
        super.setBirthDate(birthDate);
    }

    @Override
    public void setCNP(String CNP) {
        super.setCNP(CNP);
    }

    @Override
    public String getSex() {
        return super.getSex();
    }

    @Override
    public String getPhoneNo() {
        return super.getPhoneNo();
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public Date getBirthDate() {
        return super.getBirthDate();
    }

    @Override
    public String getCNP() {
        return super.getCNP();
    }

    public void readUser(ResultSet in) throws SQLException {
        this.setFirstName(in.getString("firstName"));
        this.setLastName(in.getString("lastName"));
        this.setCNP(in.getString("CNP"));
        this.setEmail(in.getString("email"));
        this.setPhoneNo(in.getString("phoneNo"));
        this.setBirthDate(in.getDate("birthDate"));
        this.setSex(in.getString("sex"));

    }

    public void readUser(Scanner in) throws ParseException {
        System.out.println("First name: ");
        this.setFirstName(in.nextLine());
        System.out.println("Last name: ");
        this.setLastName(in.nextLine());
        System.out.println("CNP: ");
        this.setCNP(in.nextLine());
        System.out.println("Email: ");
        this.setEmail(in.nextLine());
        System.out.println("Phone: ");
        this.setPhoneNo(in.nextLine());
        System.out.println("Birth Date (yyyy-MM-dd): ");
        this.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(in.nextLine()));
        System.out.println("Sex: ");
        this.setSex(in.nextLine());
    }

    @Override
    public String toFile() {
        return super.toFile();
    }

    @Override
    public String toString() {
        return "{ Id=" + id + '\'' + super.toString() + "}";
    }
}