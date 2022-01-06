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
public class UserGraphEntity {
    @Id
    private String userId;
    private String name;
    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    private Set<UserGraphEntity> follows = new HashSet<>();
    @Relationship(type = "REVIEWEDBUSINESS", direction = Relationship.Direction.OUTGOING)
    private Set<BusinessGraphEntity> reviewedBusinesses = new HashSet<>();

    public UserGraphEntity(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}


