package cz.kupcik.morosystems.kafka;

import cz.kupcik.morosystems.core.dto.MessageDto;
import cz.kupcik.morosystems.core.utilities.Serializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

@Slf4j
public class Producer extends KafkaBase {

    public void produce(final MessageDto messageDto) throws IOException {

        try (final org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<>(properties)) {

            producer.send(
                    new ProducerRecord<>(TOPIC, messageDto.getIpAddress(), Serializer.serialize(messageDto)),
                    (event, ex) -> {
                        if (ex != null) {
                            log.error("Odeslani zaznamu na Kafku selhalo", ex);
                        } else {
                            log.info("Odeslan zaznam na Kafku:{}", messageDto);
                        }
                    });
        }
    }
}