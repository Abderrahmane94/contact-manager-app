package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.model.Role;
import be.polyscripts.contactmanagerapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);

    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Override
    public List<Role> findAllRole() {
        return (List<Role>) roleRepository.findAll();
    }
}
