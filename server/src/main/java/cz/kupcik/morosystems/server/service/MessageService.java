package cz.kupcik.morosystems.server.service;

import cz.kupcik.morosystems.core.dto.MessageDto;
import cz.kupcik.morosystems.server.model.Message;
import cz.kupcik.morosystems.server.repository.MessageRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@NoArgsConstructor
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

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
