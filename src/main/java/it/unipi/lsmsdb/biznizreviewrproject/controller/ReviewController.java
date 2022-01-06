package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.Business;
import it.unipi.lsmsdb.biznizreviewrproject.model.Review;
import it.unipi.lsmsdb.biznizreviewrproject.model.User;
import it.unipi.lsmsdb.biznizreviewrproject.repository.BusinessRepository;
import it.unipi.lsmsdb.biznizreviewrproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
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
            Optional<Business> business = Optional.ofNullable(businessRepository.findByBusinessId(businessId));
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
                    if (Objects.equals(review.getReviewId(), reviewId)) {
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
            Optional<Business> business = Optional.ofNullable(businessRepository.findByBusinessId(businessId));
            if (business.isPresent()) {
                List<Review> reviews = business.get().getReviews();
                for (Review review: reviews) {
                    if (Objects.equals(review.getReviewId(), reviewId)) {
                        return new ResponseEntity<>(review, HttpStatus.OK);
                    }
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private BusinessController _businessController;
    @Autowired
    private UserController _userController;

    @PostMapping("/review/{businessId}/{userId}")
    public ResponseEntity<Review> createReview(@RequestBody Review review,
                                               @PathVariable("businessId") String businessId,
                                               @PathVariable("userId") String userId) {

            UUID uuid = UUID.randomUUID();
            Review _review = new Review(uuid.toString(), userId, businessId, review.getStars(), review.getText(), review.getBusiness_name() );
            Business _business = businessRepository.findByBusinessId(businessId);
            User _user = userRepository.findByUserId(userId);
            if (_user != null & _business != null) {
                _business.getReviews().add(_review);
                _user.getReviews().add(_review);
                _businessController.updateBusiness(businessId, _business);
                _userController.updateUser(userId, _user);
                return new ResponseEntity<>(_review, HttpStatus.OK);
                }
            else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/review/{businessId}/{userId}")
    public ResponseEntity<Review> updateReview(@RequestBody Review review,
                                               @PathVariable("businessId") String businessId,
                                               @PathVariable("userId") String userId) {
        Business _business = businessRepository.findByBusinessId(businessId);
        User _user = userRepository.findByUserId(userId);
        Review _review = new Review(review.getReviewId(), userId, businessId, review.getStars(), review.getText(), review.getBusiness_name() );
        try {
            if (_user != null){
                    Review oldUserReview = getReviewById(_user.getReviews(), review.getReviewId());
                    _user.getReviews().remove(oldUserReview);
                    _user.getReviews().add(_review);
                    _userController.updateUser(userId, _user);
            if (_business != null) {
                    Review oldBusinessReview = getReviewById(_business.getReviews(), review.getReviewId());
                    _business.getReviews().remove(oldBusinessReview);
                    _business.getReviews().add(_review);
                    _businessController.updateBusiness(businessId, _business);
                   }
            return new  ResponseEntity<>(_review, HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public Review getReviewById(List<Review> reviewList, String reviewId) {
        for (Review rev: reviewList) {
            if (rev.getReviewId().compareTo(reviewId) == 0) {
                return rev;
            }
        }
        return null;
    }

    @DeleteMapping("/review/{businessId}/{userId}/{reviewId}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable("reviewId") String reviewId,
                                                   @PathVariable("businessId") String businessId,
                                                   @PathVariable("userId") String userId) {
        Business _business = businessRepository.findByBusinessId(businessId);
        User _user = userRepository.findByUserId(userId);
        try {
            if (_user != null){
                Review oldUserReview = getReviewById(_user.getReviews(), reviewId);
                _user.getReviews().remove(oldUserReview);
                _userController.updateUser(userId, _user);
                if (_business != null) {
                    Review oldBusinessReview = getReviewById(_business.getReviews(), reviewId);
                    _business.getReviews().remove(oldBusinessReview);
                    _businessController.updateBusiness(businessId, _business);
                }
                return new  ResponseEntity<>(HttpStatus.OK);
            }}
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
