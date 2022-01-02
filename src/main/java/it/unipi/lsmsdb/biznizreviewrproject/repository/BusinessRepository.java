package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.model.Business;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BusinessRepository extends MongoRepository<Business, String> {
    List<Business> findByName(String name);
    List<Business> findByCity(String city);
    List<Business> findAll();
    List<Business> findByBusinessid(String business_id);

}

