package com.company.person.user;

import com.company.databases.UserDatabase;

import java.sql.*;
import java.text.*;
import java.util.*;

public class UserSingleton {
    private static UserSingleton instance;
    private static int id = 0;


    private UserSingleton(){}

    public static UserSingleton getInstance() {
        if(instance == null){
            instance = new UserSingleton();
        }
        return instance;
    }

    public static void incrementId(int number){
        UserSingleton.id = UserSingleton.id + number;
    }

    public User createUser(Scanner cin) throws ParseException {
        return new User(id++, cin);
    }

    public User createUser(ResultSet cin) throws SQLException {
        return new User(id++, cin);
    }
}
