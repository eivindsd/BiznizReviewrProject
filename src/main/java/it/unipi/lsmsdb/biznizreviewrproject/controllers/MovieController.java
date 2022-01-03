package it.unipi.lsmsdb.biznizreviewrproject.controllers;

import it.unipi.lsmsdb.biznizreviewrproject.entities.MovieEntity;
import it.unipi.lsmsdb.biznizreviewrproject.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController()
@RequestMapping(value = "/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;

    @GetMapping(value = "/")
    public Flux<MovieEntity> all() {
        return this.movieRepository.findAll();
    }

    @PostMapping(path = "/")
    public Mono<MovieEntity> create(@RequestBody MovieEntity movie) {
        return this.movieRepository.save(movie);
    }

    @GetMapping(value = "/{id}")
    public Mono<MovieEntity> get(@PathVariable("id") String title) {
        return Mono.just(title)
                .flatMap(movieRepository::findById)
                .switchIfEmpty(Mono.error(new Exception()));
    }
/*
    @PutMapping(path = "/{id}")
    public Mono<MovieEntity> update(@PathVariable("id") String title, @RequestBody MovieEntity movie) {
    return this.movieRepository.findById(title)
            .map(m -> {
                m.setTitle(movie.getTitle());
                m.setDescription(movie.getDescription());

                return m;
            })
            .flatMap((movieRepository::save));
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Void> delete(@PathVariable("id") String title) {
        return movieRepository.deleteById(title);
    }

 */
}


    /*@PutMapping
    MovieEntity createOrUpdateMovie(@RequestBody MovieEntity newMovie) {
        return movieRepository.save(newMovie);
    }

    @GetMapping(value = { "", "/" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/by-title")
    MovieEntity byTitle(@RequestParam String title) {
        return movieRepository.findOneByTitle(title);
    }

    @DeleteMapping("/movies/{id}")
    void delete(@PathVariable String id) {
        movieRepository.deleteById(id);
    }
}*/
