package it.unipi.lsmsdb.biznizreviewrproject.repositories;


import it.unipi.lsmsdb.biznizreviewrproject.entities.MovieEntity;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;



public interface MovieRepository extends ReactiveNeo4jRepository<MovieEntity, String> {


}
