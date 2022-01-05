package it.unipi.lsmsdb.biznizreviewrproject.controllers;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;


public class NeoJavaLesson implements AutoCloseable{

    private final Driver driver;
    private final String uri = "neo4j://localhost:7687";
    private final String user = "neo4j";
    private final String password = "secret";


    public NeoJavaLesson() {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }


    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void follow(String userId1, String userId2) {

    }
}
