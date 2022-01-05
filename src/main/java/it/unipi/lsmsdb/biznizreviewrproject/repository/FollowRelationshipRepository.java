package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.entities.FollowRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface FollowRelationshipRepository extends Neo4jRepository<FollowRelationship, Long> {

}
