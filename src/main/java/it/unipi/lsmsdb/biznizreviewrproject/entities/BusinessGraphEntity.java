package it.unipi.lsmsdb.biznizreviewrproject.entities;


import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Business")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessGraphEntity {
    @Id
    private String businessId;
    @Property("name")
    private String businessName;
}
