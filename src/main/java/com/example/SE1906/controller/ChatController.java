package com.example.SE1906.controller;

import com.example.SE1906.model.Chat;
import com.example.SE1906.service.AuthService;
import com.example.SE1906.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatController {
    private final ChatService chatService;
    @Autowired
    private AuthService authService;
    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader String token) {
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }
        return ResponseEntity.ok(chatService.getAll());
    }
    @PostMapping
    public ResponseEntity<?> addChat(@RequestBody Chat chat,@RequestHeader String token) {
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }

        chatService.add(chat);
        return ResponseEntity.ok("Chat successfully added");
    }

    @PutMapping
    public ResponseEntity<?> editChat(@RequestBody Chat chat,@RequestHeader String token) {
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }

        chatService.update(chat);
        return ResponseEntity.ok(chat);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteChat(@RequestBody Chat chat,@RequestHeader String token) {
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }

        chatService.delete(chat);
        return ResponseEntity.ok("Chat successfully deleted");
    }
}
