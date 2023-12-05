package cz.kupcik.morosystems.kafka;

import cz.kupcik.morosystems.core.dto.MessageDto;
import cz.kupcik.morosystems.core.utilities.Serializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Consumer extends KafkaBase {

    private List<MessageDto> transformRecords(final ConsumerRecords<String, String> records) {
        List<MessageDto> newItems = new ArrayList<>();
        for (ConsumerRecord<String, String> record : records) {
            String value = record.value();
            try {
                newItems.add((MessageDto) Serializer.deserialize(value));
            } catch (Exception e) {
                log.error("Deserializace neúspěšná.", e);
            }
        }
        return newItems;
    }
    public List<MessageDto> consume() {

        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "Kafka Morosystems");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        try (final org.apache.kafka.clients.consumer.Consumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Arrays.asList(TOPIC));
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            return transformRecords(records);
        }
    }
}