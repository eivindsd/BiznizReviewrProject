package it.unipi.lsmsdb.biznizreviewrproject.model;


import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Business")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessGraphEntity {
    @Id
    private String businessId;
    private String name;

}

