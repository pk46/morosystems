package cz.morosystems.server.service;

import cz.morosystems.dto.MessageDto;
import cz.morosystems.server.model.Message;
import cz.morosystems.server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(final Message message) {
        messageRepository.save(message);
    }

    public Message transformtDtoIntoMessage(MessageDto messageDto) {
        return Message.builder()
                .ipAddress(messageDto.getIpAddress())
                .sent(messageDto.getTimestamp())
                .created(LocalDateTime.now())
                .memoryUsage(messageDto.getMemoryUsage())
                .cpuUsage(messageDto.getCpuUsage())
                .build();
    }
}
