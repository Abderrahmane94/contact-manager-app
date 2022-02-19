package be.polyscripts.contactmanagerapp.ressource;

import be.polyscripts.contactmanagerapp.dto.UserDTO;
import be.polyscripts.contactmanagerapp.model.User;
import be.polyscripts.contactmanagerapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    private final Logger LOGGER = LoggerFactory.logger(CompanyResource.class);


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        LOGGER.info(" Entering getAllUsers API");
        List<User> users = userService.findAllUsers();
        LOGGER.info(" Leaving getAllUsers API");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        LOGGER.info(" Entering addUser API");
        User newUser = userService.addUser(user);
        LOGGER.info(" Leaving addUser API");
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}
