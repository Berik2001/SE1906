package com.example.SE1906.service;

import com.example.SE1906.model.Message;
import com.example.SE1906.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    public List<Message> getAllChatById(Long chatId) {
        List<Message> messageList = this.messageRepository.findAllByChatId(chatId);
        return messageList;
    }
    public void add(Message message){
        messageRepository.save(message);
    }

    public void update(Message message){
        messageRepository.save(message);
    }


    public void delete(Message message) {
        messageRepository.delete(message);
    }
}
