package com.example.SE1906.controller;


import com.example.SE1906.model.User;
import com.example.SE1906.repository.UserRepository;
import com.example.SE1906.service.AuthService;
import com.example.SE1906.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
@Autowired
    private final UserRepository userRepository;
@Autowired
private AuthService authService;
private  final UserService userService;

    @GetMapping({"","/"})
    public ResponseEntity<?> getAll(@RequestHeader String token) {
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping({"/create"})
    public  ResponseEntity<?> add(@RequestBody User user,@RequestHeader String token){
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }

        User savedUser = userService.add(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping({"/update/{id}"})
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable long id,@RequestHeader String token){
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }

        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) return ResponseEntity.notFound().build();
        user.setId(id);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping({"/delete"})
    public ResponseEntity<?> delete(User user, @RequestHeader String token) {
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }
userRepository.delete(user);
        return ResponseEntity.ok("The user successfully deleted");


    }


}
