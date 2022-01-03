package it.unipi.lsmsdb.biznizreviewrproject.repositorie;

import it.unipi.lsmsdb.biznizreviewrproject.model.PersonEntity;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface PersonRepository extends ReactiveNeo4jRepository<PersonEntity, String> {

}