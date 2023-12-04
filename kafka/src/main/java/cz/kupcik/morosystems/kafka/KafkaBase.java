package cz.kupcik.morosystems.kafka;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Slf4j
abstract public class KafkaBase {
    public static final String TOPIC = "MOROSYSTEMS";
    protected Properties properties = new Properties();

    public KafkaBase() {
        try {
            String path = new File(ClassLoader.getSystemResource("kafka.properties").getFile()).getAbsolutePath();
            loadConfig(path);
        } catch (Exception e) {
            log.error("Selhalo načtení Kafka konfigurace.", e);
        }
    }

    private void loadConfig(final String configFile) throws IOException {
        if (!Files.exists(Paths.get(configFile))) {
            throw new IOException(configFile + " nenalezen.");
        }
        try (InputStream inputStream = new FileInputStream(configFile)) {
            properties.load(inputStream);
        }
    }
}