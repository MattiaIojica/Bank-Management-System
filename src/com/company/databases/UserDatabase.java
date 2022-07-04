package com.company.databases;

import com.company.config.DatabaseConfig;
import com.company.person.user.User;
import com.company.person.user.UserFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDatabase {

    private static UserDatabase instance;
    private final UserFactory userFactory = UserFactory.getInstance();
    private final Connection connection = DatabaseConfig.getDatabaseConnection();


    private UserDatabase(){
        create();
    }

    public static UserDatabase getInstance() {
        if(instance == null){
            instance = new UserDatabase();
        }
        return instance;
    }

    public User getUserById(int id) {
        String selectSql = "SELECT * FROM user WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByCNP(int cnp) {
        String selectSql = "SELECT * FROM user WHERE cnp=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(4, cnp);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void create(){

        String createSQL = "CREATE TABLE IF NOT EXISTS Users " +
                "(id int NOT NULL,"+
                "firstName varchar(45) NOT NULL,"+
                "lastName varchar(45) NOT NULL,"+
                "CNP varchar(20) NOT NULL,"+
                "email varchar(45) DEFAULT NULL,"+
                "phoneNo varchar(20) DEFAULT NULL,"+
                "birthDate date DEFAULT NULL,"+
                "sex varchar(5) DEFAULT NULL,"+
                "PRIMARY KEY (id),"+
                "UNIQUE KEY CNP_UNIQUE (CNP))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(User user){

        String insertSQL = "INSERT INTO Users (id, firstName, lastName, CNP, email, phoneNo, birthDate, sex) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try{
            PreparedStatement prepareStatement = connection.prepareStatement(insertSQL);
            prepareStatement.setInt(1, user.getId());
            prepareStatement.setString(2, user.getFirstName());
            prepareStatement.setString(3, user.getLastName());
            prepareStatement.setString(4, user.getCNP());
            prepareStatement.setString(5, user.getEmail());
            prepareStatement.setString(6, user.getPhoneNo());
            prepareStatement.setString(7, (new SimpleDateFormat("yyyy-MM-dd")).format(user.getBirthDate()));
            prepareStatement.setString(8, user.getSex());
            prepareStatement.execute();
            prepareStatement.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public List<User> read(){
        String selectSQL = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectSQL);
            while(result.next()) {
                User user = userFactory.createUser(result);
                users.add(user);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return users;
    }

    public void update(User user){
        String updateSQL = "UPDATE Users SET firstName = ?, lastName = ?, CNP = ?, email = ?, phoneNo = ?, birthDate = ?, sex = ? WHERE id = ?";
        try{

            PreparedStatement preparedStmt = connection.prepareStatement(updateSQL);
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
            System.out.println(e);
        }
    }


    public void delete(User user){
        String deleteSQL = "DELETE FROM Users WHERE id = ?";
        try{
            PreparedStatement prepareStatement = connection.prepareStatement(deleteSQL);
            prepareStatement.setInt(1, user.getId());
            prepareStatement.execute();
            prepareStatement.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private User mapToUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getDate(7),resultSet.getString(8));
        }
        return null;
    }
}
