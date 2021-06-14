package com.example.SE1906.service;


import com.example.SE1906.model.Auth;
import com.example.SE1906.model.Message;
import com.example.SE1906.model.User;
import com.example.SE1906.repository.AuthRepository;
import com.example.SE1906.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;
    public Boolean login(String login, String password){
        return  authRepository.existsByLoginAndPassword(login,password);

    }
    public void register(Auth auth) {
            authRepository.save(auth);

    }
    public String getToken(String token) {
        Auth auth = authRepository.findAuthByToken(token);
        if(auth != null) {
            return "exists";
        } else {
            return "Failed";
        }
    }
    public void add(Auth auth) {
        authRepository.save(auth);
    }

    public void delete(Auth auth) {
        authRepository.delete(auth);
    }

    public void update(Auth auth) {
        authRepository.save(auth);
    }
}
