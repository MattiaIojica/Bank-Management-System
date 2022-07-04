package com.company.person.user;

import com.company.person.user.User;

import java.util.List;

public interface UserInterface {
    public List<User> getUsers(User user);

    public void loadFromFile();

    public void uploadToFile();

    public void addUser(User user);

    public void deteleUser(User user);
}
