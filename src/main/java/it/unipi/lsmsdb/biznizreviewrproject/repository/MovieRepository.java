package it.unipi.lsmsdb.biznizreviewrproject.repository;


import it.unipi.lsmsdb.biznizreviewrproject.model.MovieEntity;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;



public interface MovieRepository extends ReactiveNeo4jRepository<MovieEntity, String> {


}
