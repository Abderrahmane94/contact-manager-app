package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> findAllUsers();
}
