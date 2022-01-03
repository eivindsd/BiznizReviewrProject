package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.User;
import it.unipi.lsmsdb.biznizreviewrproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<User> users;
            //Creates a Pageable object - page: zero-based page index (must not be negative),
            //size: number of items in a page to be returned, must be greater than 0.
            Pageable paging =  PageRequest.of(page, size);
            Page<User> pageUsers;
            //Page<T> findAll(Pageable pageable) returns a Page of entities meeting the paging
            //condition provided by Pageable object.
            pageUsers = userRepository.findAll(paging);
            users = pageUsers.getContent();

            return new ResponseEntity<>(users, HttpStatus.OK);
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

    @GetMapping("/user/name/{name}/{password}")
    public ResponseEntity<User> getUserByNameAndPassword(@PathVariable("name") String name, @PathVariable("password") String password) {
        try {
            List<User> users = new ArrayList<>(userRepository.findByName(name));
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            boolean passwordmatch = false;
            User usermatch = null;
            for(User user: users) {
                if(Objects.equals(user.getPassword(), password)) {
                    usermatch = user;
                    passwordmatch = true;
                }
            }
            if(!passwordmatch) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usermatch, HttpStatus.OK);

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
