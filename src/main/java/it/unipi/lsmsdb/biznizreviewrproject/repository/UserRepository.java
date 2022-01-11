package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


public interface UserRepository extends MongoRepository<User, String> {

    User findByUserId(String userId);
    List<User> findByName(String name);
    User deleteByUserId(String userId);
    List<User> findByNameStartingWith(String regex);
}
