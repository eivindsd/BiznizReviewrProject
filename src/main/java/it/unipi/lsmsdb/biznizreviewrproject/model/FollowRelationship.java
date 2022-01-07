package it.unipi.lsmsdb.biznizreviewrproject.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@AllArgsConstructor
@NoArgsConstructor
@RelationshipProperties
public class FollowRelationship {
    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private UserGraphEntity userGraphEntity;
}
