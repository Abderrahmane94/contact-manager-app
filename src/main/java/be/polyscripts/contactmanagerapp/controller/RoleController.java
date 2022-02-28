package be.polyscripts.contactmanagerapp.controller;

import be.polyscripts.contactmanagerapp.dto.UserDTO;
import be.polyscripts.contactmanagerapp.model.Role;
import be.polyscripts.contactmanagerapp.service.RoleService;
import be.polyscripts.contactmanagerapp.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/role")
@CommonsLog
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        log.info(" Entering getAllRoles API");
        List<Role> roles = roleService.findAllRole();
        log.info(" Leaving getAllRoles API");
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Role> addContact(@RequestBody Role role) {
        log.info(" Entering addRole API");
        Role newRole = roleService.addRole(role);
        log.info(" Leaving addRole API");
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody UserDTO form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}