package it.unipi.lsmsdb.biznizreviewrproject.repositories;

import it.unipi.lsmsdb.biznizreviewrproject.entities.FollowRelationship;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface FollowRelationshipRepository extends ReactiveNeo4jRepository<FollowRelationship, Long> {

}
