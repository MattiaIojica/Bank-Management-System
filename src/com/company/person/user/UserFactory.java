package com.company.person.user;

import java.sql.*;
import java.text.*;
import java.util.*;

public class UserFactory {
    private static UserFactory instance;
    private static int id = 0;


    private UserFactory(){}

    public static UserFactory getInstance() {
        if(instance == null){
            instance = new UserFactory();
        }
        return instance;
    }

    public static void incrementId(int number){
        UserFactory.id = UserFactory.id + number;
    }

    public User createUser(Scanner cin) throws ParseException {
        return new User(id++, cin);
    }

    public User createUser(ResultSet cin) throws SQLException {
        return new User(id++, cin);
    }

}
