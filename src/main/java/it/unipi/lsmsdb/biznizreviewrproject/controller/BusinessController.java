package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.Business;
import it.unipi.lsmsdb.biznizreviewrproject.model.BusinessGraphEntity;
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

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BusinessController {

    @Autowired
    BusinessRepository businessRepository;

    @GetMapping("business/{businessId}")
    public ResponseEntity<Business> getBusinessById(@PathVariable("businessId") String businessId) {
        try {
            Optional<Business> business = Optional.ofNullable(businessRepository.findByBusinessId(businessId));
            return business.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/business/name/{name}")
    public ResponseEntity<List<Business>> getBusinessesByName(@PathVariable("name") String name) {
        try {
            List<Business> businesses = new ArrayList<>(businessRepository.findByName(name));
            if (businesses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(businesses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/business/search/{name}")
    public ResponseEntity<List<Business>> getAllBusinessesContainingString(@PathVariable("name") String name) {
        List<Business> allBusinesses = businessRepository.findByNameStartingWith(name);

        if (!allBusinesses.isEmpty()) {
            return new ResponseEntity<>(allBusinesses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
            List<Business> businesses = new ArrayList<>(businessRepository.findByCity(city));
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
            List<Business> businesses = new ArrayList<>(businessRepository.findByCountry(country));
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
            List<Business> businesses = new ArrayList<>(businessRepository.findByState(state));
            if (businesses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(businesses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    BusinessGraphController businessGraphController;

    @PostMapping("/business")
    public ResponseEntity<Business> createBusiness(@RequestBody Business business) {
        try {
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            ResponseEntity<Business> _business = new ResponseEntity<>(businessRepository.save(new Business(uuidAsString, business.getName(),
            business.getCountry(), business.getCity(), business.getState(), business.getTopTags(), business.getCategories(), null)), HttpStatus.CREATED);
            if (_business.getStatusCodeValue() == 201) {
                ResponseEntity<BusinessGraphEntity> graphBusiness = businessGraphController.createBusiness(business, uuidAsString);
                if (graphBusiness.getStatusCodeValue() != 201) {
                    deleteBusiness(business.getBusinessId());
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return _business;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/business/{businessId}")
    public ResponseEntity<Business> updateBusiness(@PathVariable("businessId") String businessId, @RequestBody Business business) {
        Optional<Business> businessData = Optional.ofNullable(businessRepository.findByBusinessId(businessId));
        if (businessData.isPresent()) {
            Business _business = businessData.get();
            if(business.getName() != null) {
                _business.setName(business.getName());
            }
            if(business.getCountry() != null) {
                _business.setCountry(business.getCountry());
            }
            if(business.getCity() != null) {
                _business.setCity(business.getCity());
            }
            if(business.getState() != null) {
                _business.setState(business.getState());
            }
            if(business.getTopTags() != null) {
                _business.setTopTags(business.getTopTags());
            }
            if(business.getCategories() != null) {
                _business.setCategories(business.getCategories());
            }
            if(business.getReviews() != null) {
                _business.setReviews(business.getReviews());
            }
            return new ResponseEntity<>(businessRepository.save(_business), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/business/{businessid}")
    public ResponseEntity<HttpStatus> deleteBusiness(@PathVariable("businessid") String businessId) {
        try {
            ResponseEntity<Business> deleteResponse = new ResponseEntity<>(businessRepository.deleteByBusinessId(businessId), HttpStatus.OK);
            if (deleteResponse.getStatusCodeValue() == 200) {
                ResponseEntity<HttpStatus> graphBusinessResponse = businessGraphController.deleteUser(businessId);
                if (graphBusinessResponse.getStatusCodeValue() != 200) {
                    createBusiness(deleteResponse.getBody());
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
         catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
