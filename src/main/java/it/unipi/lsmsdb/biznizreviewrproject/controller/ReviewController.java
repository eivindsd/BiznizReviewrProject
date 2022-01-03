package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.Business;
import it.unipi.lsmsdb.biznizreviewrproject.model.Review;
import it.unipi.lsmsdb.biznizreviewrproject.model.User;
import it.unipi.lsmsdb.biznizreviewrproject.repository.BusinessRepository;
import it.unipi.lsmsdb.biznizreviewrproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("review/business/{businessid}")
    public ResponseEntity<List<Review>> getReviewsByBusinessId(@PathVariable("businessid") String businessId) {
        try {
            Optional<Business> business = Optional.ofNullable(businessRepository.findByBusinessid(businessId));
            if (business.isPresent()) {
                List<Review> reviews = business.get().getReviews();
                if (!reviews.isEmpty()) {
                    return new ResponseEntity<>(reviews, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("review/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable("userId") String userId) {
        try {
            Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId));
            if (user.isPresent()) {
                List<Review> reviews = user.get().getReviews();
                if (!reviews.isEmpty()) {
                    return new ResponseEntity<>(reviews, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("review/user/{userId}/{reviewId}")
    public ResponseEntity<Review> getReviewByUser(@PathVariable("userId") String userId,
                                                  @PathVariable("reviewId") String reviewId) {
        try {
            Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId));
            if (user.isPresent()) {
                List<Review> reviews = user.get().getReviews();
                for (Review review: reviews) {
                    if (Objects.equals(review.getReview_id(), reviewId)) {
                        return new ResponseEntity<>(review, HttpStatus.OK);
                    }
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("review/business/{businessId}/{reviewId}")
    public ResponseEntity<Review> getReviewByBusiness(@PathVariable("businessId") String businessId,
                                                  @PathVariable("reviewId") String reviewId) {
        try {
            Optional<Business> business = Optional.ofNullable(businessRepository.findByBusinessid(businessId));
            if (business.isPresent()) {
                List<Review> reviews = business.get().getReviews();
                for (Review review: reviews) {
                    if (Objects.equals(review.getReview_id(), reviewId)) {
                        return new ResponseEntity<>(review, HttpStatus.OK);
                    }
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
