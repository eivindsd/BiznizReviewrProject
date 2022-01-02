package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.unipi.lsmsdb.biznizreviewrproject.repository.BusinessRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BusinessController {

    @Autowired
    BusinessRepository businessRepository;

    @GetMapping("business/{businessid}")
    public ResponseEntity<List<Business>> getBusinessById(@PathVariable("businessid") String businessid) {
        try {
            List<Business> businesses = new ArrayList<>();
            businessRepository.findByBusinessid(businessid).forEach(businesses::add);
            if (businesses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(businesses, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/business/name/{name}")
    public ResponseEntity<List<Business>> geBusinessByName(@PathVariable("name") String name) {
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
}
