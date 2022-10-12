package com.crudexample.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<UserEntity> listAll(){
        return (List<UserEntity>) repository.findAll();
    }

    public void save(UserEntity user) {
        repository.save(user);
    }

    public UserEntity get(Integer id) throws UserEntityNotFoundException {

        Optional<UserEntity> result = repository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw  new UserEntityNotFoundException("Could not find any users with by ID " + id);
    }
}
