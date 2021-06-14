package com.example.SE1906.service;


import com.example.SE1906.model.Chat;
import com.example.SE1906.model.User;
import com.example.SE1906.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    public List<Chat> getAll() {
        return this.chatRepository.findAll();
    }
    public void add(Chat chat) {
        chatRepository.save(chat);
    }

    public void update(Chat chat) {
        chatRepository.save(chat);
    }

    public void delete(Chat chat) {
        chatRepository.delete(chat);
    }
}
