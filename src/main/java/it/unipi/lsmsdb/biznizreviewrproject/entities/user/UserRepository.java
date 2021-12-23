package it.unipi.lsmsdb.biznizreviewrproject.entities.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    public List<User> findByName(String name);

}
