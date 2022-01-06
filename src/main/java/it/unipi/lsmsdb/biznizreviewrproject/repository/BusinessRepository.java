package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.model.Business;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BusinessRepository extends MongoRepository<Business, String> {
    List<Business> findByName(String name);
    List<Business> findByCity(String city);
    List<Business> findAll();
    Business findByBusinessid(String business_id);
    List<Business> findByCountry(String string);
    List<Business> findByState(String string);
    Business deleteByBusinessid(String string);


}

