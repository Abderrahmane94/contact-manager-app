package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.model.Role;
import be.polyscripts.contactmanagerapp.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public Role addRole(Role role) {
        return roleRepository.save(role);

    }

    public Role findByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    public List<Role> findAllRole() {
        return (List<Role>) roleRepository.findAll();
    }
}
