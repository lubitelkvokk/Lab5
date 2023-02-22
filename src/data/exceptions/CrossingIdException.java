package data.exceptions;

import java.io.IOException;

public class CrossingIdException extends IOException {
    public CrossingIdException() {
        super();
    }

    public CrossingIdException(String msg) {
        super(msg);
    }

    public CrossingIdException(Throwable e, String msg) {
        super(msg, e);
    }
}
