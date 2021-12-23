package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByName(String name);

}
