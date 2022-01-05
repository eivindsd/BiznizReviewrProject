package it.unipi.lsmsdb.biznizreviewrproject.repositories;

import it.unipi.lsmsdb.biznizreviewrproject.entities.FollowRelationship;
import it.unipi.lsmsdb.biznizreviewrproject.entities.UserGraphEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UserGraphRepository extends Neo4jRepository<UserGraphEntity, String> {

    UserGraphEntity findByUserId(String userId);
    void deleteByUserId(String userId);


    @Query(value = "MATCH (a:User), (b:User) \n" +
            "WHERE a.userId = :#{#userId} AND b.userId = :#" +
            "{#userId}\n)" +
            "CREATE (a)-[r:FOLLOWS]->(b)")
    @Transactional
    FollowRelationship createFollowRelationship(@Param("userId") String userId1, @Param("userId") String userId2);



}
