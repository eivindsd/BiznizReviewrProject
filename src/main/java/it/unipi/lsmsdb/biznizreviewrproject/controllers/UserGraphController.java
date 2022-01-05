package it.unipi.lsmsdb.biznizreviewrproject.controllers;

import it.unipi.lsmsdb.biznizreviewrproject.entities.UserGraphEntity;
import it.unipi.lsmsdb.biznizreviewrproject.repository.UserGraphRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping(value = "/api/graph/user")
@RequiredArgsConstructor
public class UserGraphController {
    private final UserGraphRepository userGraphRepository;

    @GetMapping(value = "/")
    public List<UserGraphEntity> all() {
        return this.userGraphRepository.findAll();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserGraphEntity> getUserById(@PathVariable("userId") String userId) {
        try {
            UserGraphEntity user = userGraphRepository.findByUserId(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch(Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<UserGraphEntity> createUser(@RequestBody UserGraphEntity user) {
        try {
            String userId = UUID.randomUUID().toString();
            //  while (userGraphRepository.findById(userId) != null) {
            //    userId = UUID.randomUUID().toString();
            // }
            System.out.println("userId = " + userId);
            System.out.println("name = " + user.getName());
            System.out.println("user = " + user);
            UserGraphEntity _user = userGraphRepository.save(new UserGraphEntity(userId, user.getName()));
            System.out.println("Saved user" + _user);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserGraphEntity> updateUser(@PathVariable("userId") String userId, @RequestBody UserGraphEntity user) {
        Optional<UserGraphEntity> tutorialData = Optional.ofNullable(userGraphRepository.findByUserId(userId));

        if (tutorialData.isPresent()) {
            UserGraphEntity _user = tutorialData.get();
            if (user.getName() != null) {
                _user.setName(user.getName());
            }
            return new ResponseEntity<>(userGraphRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") String userId) {
        try {
            userGraphRepository.deleteByUserId(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






/*
    @PutMapping(value = "/follow/")
    public Mono<FollowRelationship> follow(@RequestBody FollowDTO followDTO) {
        return this.userService.createFollowRelationship(followDTO);
    }

    public void follow(String userId1, String userId2) {
        Mono<UserGraphEntity> user1 = this.userGraphRepository.findById(userId1);
        Mono<UserGraphEntity> user2 = this.userGraphRepository.findById(userId2);

        Set

    }


 */




}
