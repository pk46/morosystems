package utilities;

import java.io.*;
import java.util.Base64;

public class Serializer {
    public static String serialize(final Object input) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        objectStream.writeObject(input);
        objectStream.close();
        return Base64.getEncoder().encodeToString(byteStream.toByteArray());
    }

    public static Object deserialize(final String input) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(input);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
        Object output = objectInputStream.readObject();
        objectInputStream.close();
        return output;
    }
}
