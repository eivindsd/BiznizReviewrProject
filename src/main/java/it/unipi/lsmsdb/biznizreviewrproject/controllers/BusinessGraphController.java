package it.unipi.lsmsdb.biznizreviewrproject.controllers;

import it.unipi.lsmsdb.biznizreviewrproject.entities.BusinessGraphEntity;
import it.unipi.lsmsdb.biznizreviewrproject.repositories.BusinessGraphRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController()
@RequestMapping(value = "/business")
@RequiredArgsConstructor
public class BusinessGraphController {
    private final BusinessGraphRepository businessGraphRepository;

    @GetMapping(value = "/")
    public Flux<BusinessGraphEntity> all() {
        return this.businessGraphRepository.findAll();
    }

}
