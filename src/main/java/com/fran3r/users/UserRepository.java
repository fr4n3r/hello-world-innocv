package com.fran3r.users;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Fran Alonso @ byteflair.com
 */
public interface UserRepository extends MongoRepository<User, String>{
}
