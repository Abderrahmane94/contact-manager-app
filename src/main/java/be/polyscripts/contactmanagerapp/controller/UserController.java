package be.polyscripts.contactmanagerapp.controller;

import be.polyscripts.contactmanagerapp.model.User;
import be.polyscripts.contactmanagerapp.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CommonsLog
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/v1/all")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info(" Entering getAllUsers API");
        List<User> users = userService.findAllUsers();
        log.info(" Leaving getAllUsers API");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        log.info(" Entering addUser API");
        User newUser = userService.addUser(user);
        log.info(" Leaving addUser API");
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}