package com.company.databases;

import com.company.user.User;
import com.company.user.UserSingleton;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDatabase {
    Connection connection;

    UserSingleton userSingleton = new UserSingleton();

    public UserDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<User> read(){
        List<User> users = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Users");
            while(result.next()) {
                User current = userSingleton.createUser(result);
                users.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return users;
    }

    public void update(User user){
        try{
            String query = "UPDATE Users SET firstName = ?, lastName = ?, CNP = ?, email = ?, phoneNo = ?, birthDate = ?, sex = ? WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getFirstName());
            preparedStmt.setString(2, user.getLastName());
            preparedStmt.setString(3, user.getCNP());
            preparedStmt.setString(4, user.getEmail());
            preparedStmt.setString(5, user.getPhoneNo());
            preparedStmt.setString(6, (new SimpleDateFormat("yyyy-MM-dd")).format(user.getBirthDate()));
            preparedStmt.setString(7, user.getSex());
            preparedStmt.setInt(8, user.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void create(User user){
        try{
            String query = "INSERT INTO Users (id, firstName, lastName, CNP, email, phoneNo, birthDate, sex) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, user.getId());
            preparedStmt.setString(2, user.getFirstName());
            preparedStmt.setString(3, user.getLastName());
            preparedStmt.setString(4, user.getCNP());
            preparedStmt.setString(5, user.getEmail());
            preparedStmt.setString(6, user.getPhoneNo());
            preparedStmt.setString(7, (new SimpleDateFormat("yyyy-MM-dd")).format(user.getBirthDate()));
            preparedStmt.setString(8, user.getSex());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(User user){
        try{
            String query = "DELETE FROM Users WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, user.getId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
