package cz.morosystems.server.service;

import cz.morosystems.server.model.Message;
import cz.morosystems.server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(String content) {
        Message message = new Message();
        message.setMessageContent(content);
        messageRepository.save(message);
    }
}
