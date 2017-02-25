package com.fran3r.users;

import com.fran3r.domain.service.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fran Alonso @ byteflair.com
 */
@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserService extends AbstractService<User, String> {
    private UserRepository userRepository;
    @Override
    public MongoRepository getRepository() {
        return userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
