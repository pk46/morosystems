package cz.kupcik.morosystems.server.service;

import cz.kupcik.morosystems.kafka.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private MessageService service;
    private Consumer consumer;

//    @Scheduled(fixedRate = 5000) // uncomment this when Kafka communication is enabled
    public void saveNewRecords() {
        consumer = new Consumer();
        consumer.consume().forEach(record -> service.saveMessage(service.transformtDtoIntoMessage(record)));

    }
}
