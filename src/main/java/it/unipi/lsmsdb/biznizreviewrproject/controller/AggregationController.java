package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.aggregationmodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/aggregations")
public class AggregationController {

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/avgstarsstate")
    public ResponseEntity<List<StarsPerState>> getAverageStarsPerState() {
        MatchOperation onlyUsa = match(new Criteria("state").ne(null));
        GroupOperation groupByStateAndAvgStars = group("state").avg("$stars").as("avgStars");
        SortOperation sortByAvgStarsState = sort(Sort.by(Sort.Direction.DESC, "avgStars"));
        Aggregation aggregation = newAggregation(onlyUsa,groupByStateAndAvgStars, sortByAvgStarsState);
        try {
            List<StarsPerState> starsPerState = new ArrayList<>(
                    mongoTemplate.aggregate(aggregation,"business", StarsPerState.class).getMappedResults());
            if(starsPerState.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(starsPerState, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/avgstarscity")
    public ResponseEntity<List<StarsPerCity>> getAverageStarsPerCity() {
        GroupOperation groupByStateAndAvgStars = group("city").avg("$stars").as("avgStars");
        MatchOperation onlyRated = match(new Criteria("avgStars").ne(0));
        SortOperation sortByAvgStarsCity = sort(Sort.by(Sort.Direction.DESC, "avgStars"));
        LimitOperation limitToXDocs = limit(11);
        Aggregation aggregation = newAggregation(groupByStateAndAvgStars, onlyRated, sortByAvgStarsCity, limitToXDocs);
        try {
            List<StarsPerCity> starsPerCity = new ArrayList<>(
                    mongoTemplate.aggregate(aggregation,"business", StarsPerCity.class).getMappedResults());
            if(starsPerCity.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(starsPerCity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/maxreviewscity")
    public ResponseEntity<List<ReviewsPerCity>> getCityMaxReviews() {
        MatchOperation reviewed = match(new Criteria("stars").gt(0));
        ProjectionOperation project = project().andInclude("city").and("reviews").size().as("numberOfReviews");
        GroupOperation groupByCityAndAvgReviews = group("city").avg("numberOfReviews").as("avgReviews");
        SortOperation sortByAvgReviewsDecs = sort(Sort.by(Sort.Direction.DESC, "avgReviews"));
        LimitOperation limitToXDocs = limit(11);
        Aggregation aggregation = newAggregation(reviewed, project, groupByCityAndAvgReviews, sortByAvgReviewsDecs, limitToXDocs);
        try {
            List<ReviewsPerCity> reviewsPerCity = new ArrayList<>(
                    mongoTemplate.aggregate(aggregation,"business", ReviewsPerCity.class).getMappedResults());
            if(reviewsPerCity.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reviewsPerCity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/amountofstarsperbusiness/{businessId}")
    public ResponseEntity<StarsPerBusiness> getStarsPerBusiness(@PathVariable("businessId") String businessId) {
        MatchOperation matchBusinessId = match(new Criteria("businessId").is(businessId).and("stars").gt(0));
        UnwindOperation unwindReviews = unwind("reviews");
        GroupOperation groupByStars = group("businessId")
                .sum(ConditionalOperators.when(Criteria.where("reviews.stars").is(5))
                        .then(1).otherwise(0)).as("amountFive")
                .sum(ConditionalOperators.when(Criteria.where("reviews.stars").is(4))
                        .then(1).otherwise(0)).as("amountFour")
                .sum(ConditionalOperators.when(Criteria.where("reviews.stars").is(3))
                        .then(1).otherwise(0)).as("amountThree")
                .sum(ConditionalOperators.when(Criteria.where("reviews.stars").is(2))
                        .then(1).otherwise(0)).as("amountTwo")
                .sum(ConditionalOperators.when(Criteria.where("reviews.stars").is(1))
                        .then(1).otherwise(0)).as("amountOne");
        Aggregation aggregation = newAggregation(matchBusinessId, unwindReviews, groupByStars);
        try {
            StarsPerBusiness starsPerBusiness = mongoTemplate.aggregate(aggregation,"business", StarsPerBusiness.class).getUniqueMappedResult();
            if(starsPerBusiness == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(starsPerBusiness, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/amountofstarsperuser/{userId}")
    public ResponseEntity<StarsPerUser> getStarsPerUser(@PathVariable("userId") String userId) {
        MatchOperation matchUserId = match(new Criteria("userId").is(userId));
        UnwindOperation unwindReviews = unwind("reviews");
        GroupOperation groupByStars = group("userId")
                .sum(ConditionalOperators.when(Criteria.where("reviews.stars").is(5))
                        .then(1).otherwise(0)).as("amountFive")
                .sum(ConditionalOperators.when(Criteria.where("reviews.stars").is(4))
                        .then(1).otherwise(0)).as("amountFour")
                .sum(ConditionalOperators.when(Criteria.where("reviews.stars").is(3))
                        .then(1).otherwise(0)).as("amountThree")
                .sum(ConditionalOperators.when(Criteria.where("reviews.stars").is(2))
                        .then(1).otherwise(0)).as("amountTwo")
                .sum(ConditionalOperators.when(Criteria.where("reviews.stars").is(1))
                        .then(1).otherwise(0)).as("amountOne");
        Aggregation aggregation = newAggregation(matchUserId, unwindReviews, groupByStars);
        try {
            StarsPerUser starsPerUser = mongoTemplate.aggregate(aggregation,"user", StarsPerUser.class).getUniqueMappedResult();
            if(starsPerUser == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(starsPerUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }










}
