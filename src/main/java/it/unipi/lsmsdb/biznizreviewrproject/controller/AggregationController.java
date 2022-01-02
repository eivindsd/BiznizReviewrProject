package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.aggregationmodels.ReviewsPerCity;
import it.unipi.lsmsdb.biznizreviewrproject.model.aggregationmodels.StarsPerCity;
import it.unipi.lsmsdb.biznizreviewrproject.model.aggregationmodels.StarsPerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@RestController
@RequestMapping("/api/aggregations")
public class AggregationController {

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/avgstarsstate")
    public List<StarsPerState> getAverageStarsPerState() {
        GroupOperation groupByStateAndAvgStars = group("state").avg("$stars").as("avgStars");
        Aggregation aggregation = newAggregation(groupByStateAndAvgStars);
        return mongoTemplate.aggregate(aggregation,"business", StarsPerState.class).getMappedResults();
    }

    @GetMapping("/avgstarscity")
    public List<StarsPerCity> getAverageStarsPerCity() {
        GroupOperation groupByStateAndAvgStars = group("city").avg("$stars").as("avgStars");
        Aggregation aggregation = newAggregation(groupByStateAndAvgStars);
        return mongoTemplate.aggregate(aggregation,"business", StarsPerCity.class).getMappedResults();
    }

    @GetMapping("/maxreviewscity")
    public List<ReviewsPerCity> getCityMaxReviews() {
        return null;
    }


}
