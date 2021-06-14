package com.example.SE1906.service;

import com.example.SE1906.model.User;
import com.example.SE1906.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User add(User user){
        return  this.userRepository.save(user);

    }

    public void update(User user){
        userRepository.save(user);
    }

    public void delete(User user){
        this.userRepository.delete(user);
    }

}
