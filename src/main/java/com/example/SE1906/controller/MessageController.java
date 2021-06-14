package com.example.SE1906.controller;


import com.example.SE1906.model.Message;
import com.example.SE1906.service.AuthService;
import com.example.SE1906.service.MessageService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/message")
public class MessageController {
    private final MessageService messageService;
    @Autowired
    private AuthService authService;

    @GetMapping({"/chat/{id}"})
    public ResponseEntity<?> getMessagesByChatId(@PathVariable(name = "id") Long chatId,@RequestHeader String token) {
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }
        return ResponseEntity.ok(messageService.getAllChatById(chatId));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createMessage(@RequestBody Message message,@RequestHeader String token) {
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }
        messageService.add(message);
        return ResponseEntity.ok("The message successfully added");
    }
    @PutMapping
    public ResponseEntity<?> editMessage(@RequestBody Message message,@RequestHeader String token) {
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }

        messageService.update(message);
        return ResponseEntity.ok(message);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteMessage(@RequestBody Message message,@RequestHeader String token) {
        if(!authService.getToken(token).equals("exists")) {
            return ResponseEntity.ok("Invalid token");
        }

        messageService.delete(message);
        return ResponseEntity.ok("The message successfully deleted");
    }

}

