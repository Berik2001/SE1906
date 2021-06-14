package com.example.SE1906.controller;

import com.example.SE1906.model.Auth;
import com.example.SE1906.model.User;
import com.example.SE1906.repository.AuthRepository;
import com.example.SE1906.repository.UserRepository;
import com.example.SE1906.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String login,@RequestParam String password){
        if(authService.login(login,password).equals(true)){
            Auth auth=authRepository.findAuthByLogin(login);
            auth.setToken(UUID.randomUUID().toString());
            authService.add(auth);
            return ResponseEntity.ok("Successfully logged");
        }
        else{
            return ResponseEntity.ok("Failed");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String name,
                                      @RequestParam String login,
                                      @RequestParam String password) {
        User user=userRepository.getUserByName(name);

        if(user!=null){
            Auth auth = new Auth();
            auth.setLogin(login);
            auth.setPassword(password);
            auth.setUserId(user.getId());
            authService.register(auth);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Auth auth) {
        authService.add(auth);
        return ResponseEntity.ok("The auth successfully added");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Auth auth) {
        authService.update(auth);
        return ResponseEntity.ok("The Auth successfully updated");
    }


    @DeleteMapping
    public ResponseEntity<?> deleteMessage(@RequestBody Auth auth) {

        authService.delete(auth);
        return ResponseEntity.ok("The auth successfully deleted");
    }
    @GetMapping({"","/"})
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(authRepository.findAll());
    }
}



