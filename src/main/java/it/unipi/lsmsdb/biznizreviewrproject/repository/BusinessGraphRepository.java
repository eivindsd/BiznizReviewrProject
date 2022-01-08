package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.model.BusinessGraphEntity;
import it.unipi.lsmsdb.biznizreviewrproject.model.UserGraphEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessGraphRepository extends Neo4jRepository<BusinessGraphEntity, String> {

    BusinessGraphEntity findByBusinessId(String businessId);
    void deleteByBusinessId(String businessId);

    @Query(value = "MATCH (a:User {userId: $userId })-[:FOLLOWS]->(friend)-[:REVIEWEDBUSINESS]->(suggestion)" +
            "RETURN suggestion LIMIT 10" )
    List<BusinessGraphEntity> getSuggestions(@Param("userId") String userId);
}
