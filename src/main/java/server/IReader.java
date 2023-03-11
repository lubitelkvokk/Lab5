package server;

import data.StudyGroup;

import java.io.IOException;

public interface IReader {

    String readCommand() throws IOException;

    StudyGroup readElement();

    String readLine() throws IOException;

    void close() throws IOException;

}
