package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.entities.Business;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BusinessRepository extends MongoRepository<Business, String> {

    public List<Business> findByName(String name);
    public List<Business> findByCity(String city);
}
