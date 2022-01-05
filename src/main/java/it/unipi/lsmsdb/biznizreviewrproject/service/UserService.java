package it.unipi.lsmsdb.biznizreviewrproject.service;


import it.unipi.lsmsdb.biznizreviewrproject.entities.FollowDTO;
import it.unipi.lsmsdb.biznizreviewrproject.entities.FollowRelationship;
import it.unipi.lsmsdb.biznizreviewrproject.entities.UserGraphEntity;
import it.unipi.lsmsdb.biznizreviewrproject.repositories.UserGraphRepository;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
@Transactional
public class UserService {

    private final UserGraphRepository userGraphRepository;

    public UserService(UserGraphRepository userGraphRepository) {
        this.userGraphRepository = userGraphRepository;
    }

    public FollowRelationship createFollowRelationship(FollowDTO followDTO) {

        return userGraphRepository.createFollowRelationship(followDTO.getUserId1(), followDTO.getUserId2());
    }

}
