package com.company.user;

import java.sql.*;
import java.text.*;
import java.util.*;

public class UserSingleton {
    private static int id = 0;

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
