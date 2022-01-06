package it.unipi.lsmsdb.biznizreviewrproject.service;


import it.unipi.lsmsdb.biznizreviewrproject.model.FollowDTO;
import it.unipi.lsmsdb.biznizreviewrproject.model.FollowRelationship;
import it.unipi.lsmsdb.biznizreviewrproject.repository.UserGraphRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserGraphRepository userGraphRepository;

    public UserService(UserGraphRepository userGraphRepository) {
        this.userGraphRepository = userGraphRepository;
    }
/*
    public FollowRelationship createFollowRelationship(FollowDTO followDTO) {

        return userGraphRepository.createFollowRelationship(followDTO.getUserId1(), followDTO.getUserId2());
    }

 */

}
