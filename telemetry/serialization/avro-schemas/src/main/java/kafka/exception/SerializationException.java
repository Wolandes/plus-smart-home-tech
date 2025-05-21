package kafka.exception;

import java.io.IOException;

public class SerializationException extends RuntimeException {
    public SerializationException(String message, IOException ex) {
        super(message);
    }
}
