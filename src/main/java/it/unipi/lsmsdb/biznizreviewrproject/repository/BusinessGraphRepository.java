package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.model.BusinessGraphDB;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface BusinessGraphRepository extends ReactiveNeo4jRepository<BusinessGraphDB, String> {

}
