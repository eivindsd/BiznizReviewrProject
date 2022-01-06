package it.unipi.lsmsdb.biznizreviewrproject.repository;

import it.unipi.lsmsdb.biznizreviewrproject.model.BusinessGraphEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BusinessGraphRepository extends Neo4jRepository<BusinessGraphEntity, String> {

    BusinessGraphEntity findByBusinessId(String businessId);
    void deleteByBusinessId(String businessId);
}
