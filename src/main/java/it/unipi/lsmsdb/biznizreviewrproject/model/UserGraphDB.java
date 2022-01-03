package it.unipi.lsmsdb.biznizreviewrproject.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node("User")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGraphDB {
    @Id
    private String userId;
    private String name;
    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    private Set<UserGraphDB> follows = new HashSet<>();
    @Relationship(type = "REVIEWEDBUSINESS", direction = Relationship.Direction.OUTGOING)
    private Set<BusinessGraphDB> reviewedBusinesses = new HashSet<>();
}


