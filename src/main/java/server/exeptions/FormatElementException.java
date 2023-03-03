package server.exeptions;

import java.io.IOException;

public class FormatElementException extends IOException {
    public FormatElementException() {
    }

    public FormatElementException(String message) {
        super(message);
    }

    public FormatElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
