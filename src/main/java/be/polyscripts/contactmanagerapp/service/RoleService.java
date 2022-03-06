package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.model.Role;

import java.util.List;

public interface RoleService {

    Role addRole(Role role);

    Role findByName(String name);

    List<Role> findAllRole();
}
