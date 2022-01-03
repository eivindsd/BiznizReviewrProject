package it.unipi.lsmsdb.biznizreviewrproject.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Person")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
    @Id
    private String name;
    private Integer born;


}
