package it.unipi.lsmsdb.biznizreviewrproject.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;


import java.util.HashSet;
import java.util.Set;

@Node("Movie")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    @Id
    private String title;
    @Property("tagline")
    private String description;
    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private Set<PersonEntity> actors = new HashSet<>();
    @Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
    private Set<PersonEntity> directors = new HashSet<>();

    //Autogenerate code: Alt + insert
}