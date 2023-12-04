package cz.kupcik.morosystems.core.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Serializer.class)
class SerializerTest {

    private static final String STRING_INPUT= "Hello";
    private static final String SERIALIZATION= "rO0ABXQABUhlbGxv";
    @Test
    void serialize() throws IOException {
        String output = Serializer.serialize(STRING_INPUT);
        assertEquals(SERIALIZATION, output);
    }

    @Test
    void deserialize() throws IOException, ClassNotFoundException {
        Object output = Serializer.deserialize(SERIALIZATION);
        assertEquals(STRING_INPUT, output);
    }
}