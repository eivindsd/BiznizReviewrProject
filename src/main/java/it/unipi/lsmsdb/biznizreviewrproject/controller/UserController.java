package it.unipi.lsmsdb.biznizreviewrproject.controller;


import it.unipi.lsmsdb.biznizreviewrproject.model.Business;
import it.unipi.lsmsdb.biznizreviewrproject.model.User;
import it.unipi.lsmsdb.biznizreviewrproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> tutorials = new ArrayList<>(userRepository.findAll());
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            //generate userid
            String userId = UUID.randomUUID().toString();
            while (userRepository.findByUserId(userId) != null) {
                userId = UUID.randomUUID().toString();
            }
            User _user = userRepository.save(new User(userId, user.getName(), user.getPassword()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId));

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
        Optional<User> tutorialData = Optional.ofNullable(userRepository.findByUserId(userId));

        if (tutorialData.isPresent()) {
            User _user = tutorialData.get();
            if (user.getName() != null) {
                _user.setName(user.getName());
            }
            if (user.getPassword() != null) {
                _user.setPassword(user.getPassword());
            }
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/name/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String name) {
        try {
            List<User> users = new ArrayList<>(userRepository.findByName(name));
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") String userId) {
        try {
            userRepository.deleteByUserId(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
