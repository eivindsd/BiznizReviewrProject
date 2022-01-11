package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.Business;
import it.unipi.lsmsdb.biznizreviewrproject.model.BusinessGraphEntity;
import it.unipi.lsmsdb.biznizreviewrproject.model.UserGraphEntity;
import it.unipi.lsmsdb.biznizreviewrproject.repository.BusinessGraphRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController()
@RequestMapping(value = "/api/graph/business")
@RequiredArgsConstructor
public class BusinessGraphController {
    private final BusinessGraphRepository businessGraphRepository;

    @PostMapping(value = "/")
    public ResponseEntity<BusinessGraphEntity> createBusiness(@RequestBody Business business, String uuid) {
        try {
            BusinessGraphEntity _business = businessGraphRepository.save(new BusinessGraphEntity(uuid, business.getName()));
            return new ResponseEntity<>(_business, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PutMapping("/{businessId}")
    public ResponseEntity<BusinessGraphEntity> updateBusiness(@PathVariable("businessId") String businessId, @RequestBody BusinessGraphEntity business) {
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

    @GetMapping(value = "/suggestions/{userId}")
    public ResponseEntity<List<BusinessGraphEntity>> getSuggestion(@PathVariable("userId") String userId) {
        try {
            List<BusinessGraphEntity> suggestions = businessGraphRepository.getSuggestions(userId);
            return new ResponseEntity<>(suggestions, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{businessId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("businessId") String businessId) {
        try {
            businessGraphRepository.deleteByBusinessId(businessId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
