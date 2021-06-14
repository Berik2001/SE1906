package com.example.SE1906.repository;


import com.example.SE1906.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {

    Boolean existsByLoginAndPassword(String login, String password);

    Auth findAuthByLogin(String login);

    Auth findAuthByToken(String token);
}
