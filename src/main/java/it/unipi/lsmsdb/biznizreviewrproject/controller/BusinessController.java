package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.Business;
import it.unipi.lsmsdb.biznizreviewrproject.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.unipi.lsmsdb.biznizreviewrproject.repository.BusinessRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BusinessController {

    @Autowired
    BusinessRepository businessRepository;

    @GetMapping("business/{businessid}")
    public ResponseEntity<Business> getBusinessById(@PathVariable("businessid") String businessId) {
        try {
            Optional<Business> business = Optional.ofNullable(businessRepository.findByBusinessid(businessId));
            if (business.isPresent()) {
                return new ResponseEntity<>(business.get(), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/business/name/{name}")
    public ResponseEntity<List<Business>> getBusinessesByName(@PathVariable("name") String name) {
        try {
            List<Business> businesses = new ArrayList<>();
            businessRepository.findByName(name).forEach(businesses::add);
            if (businesses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(businesses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/business")
    public ResponseEntity<List<Business>> getAllBusinesses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Business> businesses;
            Pageable paging = PageRequest.of(page, size);
            Page<Business> businessPage;
            businessPage = businessRepository.findAll(paging);
            businesses = businessPage.getContent();
            if (businesses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(businesses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("business/city/{city}")
    public ResponseEntity<List<Business>> getBusinessesByCity(@PathVariable("city") String city) {
        try {
            List<Business> businesses = new ArrayList<>();
            businessRepository.findByCity(city).forEach(businesses::add);
            if (businesses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(businesses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("business/country/{country}")
    public ResponseEntity<List<Business>> getBusinessesByCountry(@PathVariable("country") String country) {
        try {
            List<Business> businesses = new ArrayList<>();
            businessRepository.findByCountry(country).forEach(businesses::add);
            if (businesses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(businesses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("business/state/{state}")
    public ResponseEntity<List<Business>> getBusinessesByState(@PathVariable("state") String state) {
        try {
            List<Business> businesses = new ArrayList<>();
            businessRepository.findByState(state).forEach(businesses::add);
            if (businesses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(businesses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/business")
    public ResponseEntity<Business> createBusiness(@RequestBody Business business) {
        try {
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            Business _business = businessRepository.save(new Business(uuidAsString, business.getName(),
            business.getCountry(), business.getCity(), business.getState(), business.getTopTags(), business.getStars(), business.getCategories(), null));
            return new ResponseEntity<>(_business, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/business/{businessId}")
    public ResponseEntity<Business> updateBusiness(@PathVariable("businessId") String businessId, @RequestBody Business business) {
        Optional<Business> businessData = Optional.ofNullable(businessRepository.findByBusinessid(businessId));
        if (businessData.isPresent()) {
            Business _business = businessData.get();
            _business.setName(business.getName());
            _business.setCountry(business.getCountry());
            _business.setCity(business.getCity());
            _business.setState(business.getState());
            _business.setTopTags(business.getTopTags());
            _business.setStars(business.getStars());
            _business.setCategories(business.getCategories());
            return new ResponseEntity<>(businessRepository.save(_business), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/business/{businessid}")
    public ResponseEntity<HttpStatus> deleteBusiness(@PathVariable("businessid") String businessId) {
        try {
            businessRepository.deleteByBusinessid(businessId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
         catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
