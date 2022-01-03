package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.PersonEntity;
import it.unipi.lsmsdb.biznizreviewrproject.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController()
@RequestMapping(value = "/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping(value = "/")
    public Flux<PersonEntity> all() {
        return this.personRepository.findAll();
    }


}