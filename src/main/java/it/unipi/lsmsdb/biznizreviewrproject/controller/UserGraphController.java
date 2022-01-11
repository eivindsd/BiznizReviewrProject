package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.BusinessGraphEntity;
import it.unipi.lsmsdb.biznizreviewrproject.model.User;
import it.unipi.lsmsdb.biznizreviewrproject.model.UserGraphEntity;
import it.unipi.lsmsdb.biznizreviewrproject.repository.BusinessGraphRepository;
import it.unipi.lsmsdb.biznizreviewrproject.repository.UserGraphRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@CrossOrigin
@RestController()
@RequestMapping(value = "/api/graph/user")
@RequiredArgsConstructor
public class UserGraphController {
    private final UserGraphRepository userGraphRepository;
    private final BusinessGraphRepository businessGraphRepository;

  /*  @GetMapping(value = "/")
    public List<UserGraphEntity> all() {
        return this.userGraphRepository.findAll();
    }

   */

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

    @GetMapping(value = "/following/{userId}")
    public ResponseEntity<List<UserGraphEntity>> getFollowing(@PathVariable("userId") String userId) {
        try {
            List<UserGraphEntity> following = userGraphRepository.getFollowing(userId);
            return new ResponseEntity<>(following, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/suggestions/{userId}")
    public ResponseEntity<List<UserGraphEntity>> getSuggestion(@PathVariable("userId") String userId) {
        try {
            List<UserGraphEntity> suggestions = userGraphRepository.getSuggestions(userId);
            return new ResponseEntity<>(suggestions, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<UserGraphEntity> createUser(@RequestBody User user) {
        try {
            UserGraphEntity _user = userGraphRepository.save(new UserGraphEntity(user.getUserId(), user.getName()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
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

    @PutMapping("/follow/{userId1}/{userId2}")
    public ResponseEntity<UserGraphEntity> follow(@PathVariable("userId1") String userId1, @PathVariable("userId2") String userId2) {
        Optional<UserGraphEntity> user1 = Optional.ofNullable(userGraphRepository.findByUserId(userId1));
        Optional<UserGraphEntity> user2 = Optional.ofNullable(userGraphRepository.findByUserId(userId2));
        System.out.println(user1);
        System.out.println(user2);
        if (user1.isPresent() && user2.isPresent()) {
            UserGraphEntity _user = user1.get();
            Set<UserGraphEntity> follows = _user.getFollows();
            follows.add(user2.get());
            return new ResponseEntity<>(userGraphRepository.save(_user), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/review/{userId}/{businessId}")
    public ResponseEntity<UserGraphEntity> review(@PathVariable("userId") String userId, @PathVariable("businessId") String businessId) {
        Optional<UserGraphEntity> user = Optional.ofNullable(userGraphRepository.findByUserId(userId));
        Optional<BusinessGraphEntity> business = Optional.ofNullable(businessGraphRepository.findByBusinessId(businessId));

        if (user.isPresent() && business.isPresent()) {
            UserGraphEntity _user = user.get();
            Set<BusinessGraphEntity> reviewedBusinesses = _user.getReviewedBusinesses();
            reviewedBusinesses.add(business.get());
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
