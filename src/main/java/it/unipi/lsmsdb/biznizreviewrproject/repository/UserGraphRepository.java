package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.model.FollowRelationship;
import it.unipi.lsmsdb.biznizreviewrproject.model.UserGraphEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
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
