package com.fran3r.users;

import com.fran3r.domain.service.AbstractService;
import com.fran3r.domain.service.AsyncService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fran Alonso @ byteflair.com
 */
@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class UserService extends AbstractService<User, String> {
    private AsyncService asyncService;
    private UserRepository userRepository;
    @Override
    public MongoRepository getRepository() {
        return userRepository;
    }

    public List<User> getAll(){
        asyncService.asyncMethodWithReturnType();
        return userRepository.findAll();
    }

}
