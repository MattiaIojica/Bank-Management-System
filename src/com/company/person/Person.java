package com.company.person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Person {

    private String firstName;
    private String lastName;
    private String CNP; //CNP
    private String email;
    private String phoneNo;
    private Date birthDate;
    private String sex;

    public Person() {

    }


    public Person(String firstName, String lastName, String CNP,
                String email, String phoneNo, Date birthDate, String sex){
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.email = email;
        this.phoneNo = phoneNo;
        this.birthDate = birthDate;
        this.sex = sex;
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

    protected String toFile() {
        return  firstName +
                "," + lastName +
                "," + CNP +
                "," + email +
                "," + phoneNo +
                "," + (new SimpleDateFormat("yyyy-MM-dd")).format(birthDate) +
                "," + sex;
    }

    @Override
    public String toString() {
        return "" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", CNP='" + CNP + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", birthDate=" + birthDate +
                ", sex='" + sex + '\'';
    }
}