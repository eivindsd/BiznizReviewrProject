package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.model.PersonEntity;
import it.unipi.lsmsdb.biznizreviewrproject.model.UserGraphDB;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface UserGraphRepository extends ReactiveNeo4jRepository<UserGraphDB, String> {

}
