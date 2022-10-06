package com.crudexample.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
