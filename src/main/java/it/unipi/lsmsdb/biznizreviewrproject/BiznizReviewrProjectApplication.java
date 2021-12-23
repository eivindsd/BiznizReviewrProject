package it.unipi.lsmsdb.biznizreviewrproject;

import it.unipi.lsmsdb.biznizreviewrproject.entities.User;
import it.unipi.lsmsdb.biznizreviewrproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;


@SpringBootApplication
public class BiznizReviewrProjectApplication implements CommandLineRunner {

    //Får error ved å ha flere repositories samtidig ... men bør nok lese meg litt opp på det
    @Autowired
    private UserRepository userRepository;
    //private BusinessRepository businessRepository;
    //private ReviewRepository reviewRepository;


    //kun lov med unike navn på brukere
    public void createUser(Object _id, String user_id, String name) {
        if(findUser(name) == null) {
            userRepository.save(new User(_id, user_id, name));
        }
    }

    //nå er det kun for unike brukernavn, funker ikke hvis flere heter det samme
    public User findUser(String username) {
        return userRepository.findByName(username);
    }

    //hvilke attributter skal kunne oppdateres?
    public void updateUser(String name) {
    }


    public void deleteUser(String name) {
        if(findUser(name) != null) {
            userRepository.delete((User) userRepository.findByName(name));
        }
    }


    /*
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
    public void deleteBusiness(String name) {

    }

*/

    public static void main(String[] args) {
        SpringApplication.run(BiznizReviewrProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        //Search for User :
        System.out.println(findUser("silly"));

        //Search for Business by City:
        //System.out.println(businessRepository.findByCity("Portland"));

        //Search for a Business by Name:
        //System.out.println(businessRepository.findByName("Oskar Blues Taproom"));

    }
}
