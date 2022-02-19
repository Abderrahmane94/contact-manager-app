package be.polyscripts.contactmanagerapp.ressource;

import be.polyscripts.contactmanagerapp.dto.UserDTO;
import be.polyscripts.contactmanagerapp.model.Role;
import be.polyscripts.contactmanagerapp.service.RoleService;
import be.polyscripts.contactmanagerapp.service.UserService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleResource {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public RoleResource(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    private final Logger LOGGER = LoggerFactory.logger(CompanyResource.class);


    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        LOGGER.info(" Entering getAllRoles API");
        List<Role> roles = roleService.findAllRole();
        LOGGER.info(" Leaving getAllRoles API");
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Role> addContact(@RequestBody Role role) {
        LOGGER.info(" Entering addRole API");
        Role newRole = roleService.addRole(role);
        LOGGER.info(" Leaving addRole API");
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody UserDTO form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

}


