package org.Bliss.data.repositories;

import org.Bliss.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
