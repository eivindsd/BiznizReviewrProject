package it.unipi.lsmsdb.biznizreviewrproject.controller;

import it.unipi.lsmsdb.biznizreviewrproject.model.UserGraphDB;
import it.unipi.lsmsdb.biznizreviewrproject.repository.UserGraphRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController()
@RequestMapping(value = "/business")
@RequiredArgsConstructor
public class BusinessGraphController {
    private final UserGraphRepository userGraphRepository;

    @GetMapping(value = "/")
    public Flux<UserGraphDB> all() {
        return this.userGraphRepository.findAll();
    }

}
