package it.unipi.lsmsdb.biznizreviewrproject;

import it.unipi.lsmsdb.biznizreviewrproject.entities.business.Business;
import it.unipi.lsmsdb.biznizreviewrproject.entities.business.BusinessRepository;
import it.unipi.lsmsdb.biznizreviewrproject.entities.review.ReviewRepository;
import it.unipi.lsmsdb.biznizreviewrproject.entities.user.User;
import it.unipi.lsmsdb.biznizreviewrproject.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class BiznizReviewrProjectApplication implements CommandLineRunner {

    //Får error ved å ha flere repositories samtidig ... men bør nok lese meg litt opp på det
    @Autowired
    //private UserRepository userRepository;
    private BusinessRepository businessRepository;
    //private ReviewRepository reviewRepository;

/*
    public void createUser(String id, String name) {
        userRepository.save(new User(id, name));
    }

    public List<User> findUsers(String username) {
        return new ArrayList<>(userRepository.findByName(username));
    }

    //hvilke attributter skal kunne oppdateres?
    public void updateUser(String name) {
    }

    //Tror det letteste er å delete på navn, men "_id" i mongodb gjør det litt vanskelig
    public void deleteUser(String id) {

    }

 */

    public void createBusiness(String business_id, String name, String city, String state, String stars, String categories) {
        businessRepository.save(new Business(business_id, name, city, state, stars, categories));
    }

    public List<Business> findBusinessesByName(String name) {
        return new ArrayList<>(businessRepository.findByName(name));
    }

    public List<Business> findBusinessesByCity(String city) {
        return new ArrayList<>(businessRepository.findByCity(city));
    }


    //hvilke attributter skal kunne oppdateres?
    public void updateBusiness(String name) {

    }

    //Samme som deleteUser, usikker på hvordan gjøre dette på best måte
    public void deleteBusiness(String id) {

    }



    public static void main(String[] args) {
        SpringApplication.run(BiznizReviewrProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //Create a new User:
        //createUser("id", "Eivind");

        //Search for User:
        //System.out.println(findUsers("Jane"));

        //Search for Business by City:
        //System.out.println(businessRepository.findByCity("Portland"));

        //Search for a Business by Name:
        //System.out.println(businessRepository.findByName("Oskar Blues Taproom"));

    }
}
