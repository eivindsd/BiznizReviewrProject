package it.unipi.lsmsdb.biznizreviewrproject.controllers;

import it.unipi.lsmsdb.biznizreviewrproject.entities.BusinessGraphEntity;
import it.unipi.lsmsdb.biznizreviewrproject.entities.UserGraphEntity;
import it.unipi.lsmsdb.biznizreviewrproject.repository.BusinessGraphRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping(value = "/api/graph/business")
@RequiredArgsConstructor
public class BusinessGraphController {
    private final BusinessGraphRepository businessGraphRepository;

    /*
    @GetMapping(value = "/")
    public List<BusinessGraphEntity> all() {
        return this.businessGraphRepository.findAll();
    }

     */

    @PostMapping(value = "/")
    public ResponseEntity<BusinessGraphEntity> createUser(@RequestBody BusinessGraphEntity business) {
        try {
            String userId = UUID.randomUUID().toString();
            //  while (userGraphRepository.findById(userId) != null) {
            //    userId = UUID.randomUUID().toString();
            // }
            BusinessGraphEntity _business = businessGraphRepository.save(new BusinessGraphEntity(userId, business.getName()));
            System.out.println("Saved user" + _business);
            return new ResponseEntity<>(_business, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PutMapping("/{businessId}")
    public ResponseEntity<BusinessGraphEntity> updatebusiness(@PathVariable("businessId") String businessId, @RequestBody BusinessGraphEntity business) {
        Optional<BusinessGraphEntity> tutorialData = Optional.ofNullable(businessGraphRepository.findByBusinessId(businessId));

        if (tutorialData.isPresent()) {
            BusinessGraphEntity _business = tutorialData.get();
            if (business.getName() != null) {
                _business.setName(business.getName());
            }
            return new ResponseEntity<>(businessGraphRepository.save(_business), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{businessId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("businessId") String businessId) {
        try {
            businessGraphRepository.deleteByBusinessId(businessId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
