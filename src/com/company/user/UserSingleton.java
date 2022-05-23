package com.company.user;

import java.sql.*;
import java.text.*;
import java.util.*;

public class UserSingleton {
    private static int id = 0;

    public static void incrementId(int number){
        UserSingleton.id = UserSingleton.id + number;
    }

    public User createUser(Scanner in) throws ParseException {
        return new User(id++, in);
    }

    public User createUser(ResultSet in) throws SQLException {
        return new User(id++, in);
    }
}
