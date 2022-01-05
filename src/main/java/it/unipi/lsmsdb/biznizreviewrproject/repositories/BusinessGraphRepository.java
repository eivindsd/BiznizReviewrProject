package it.unipi.lsmsdb.biznizreviewrproject.repositories;

import it.unipi.lsmsdb.biznizreviewrproject.entities.BusinessGraphEntity;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface BusinessGraphRepository extends ReactiveNeo4jRepository<BusinessGraphEntity, String> {

}
