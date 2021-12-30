package it.unipi.lsmsdb.biznizreviewrproject.repositories;

import it.unipi.lsmsdb.biznizreviewrproject.entities.PersonEntity;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface PersonRepository extends ReactiveNeo4jRepository<PersonEntity, String> {

}