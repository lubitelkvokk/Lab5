package client.executor;

import client.Receiver;
import server.Reader;

import java.io.IOException;

//TODO Можно назвать класс как Terminal

/**
 * Суть этого класса заключается в том, что он выполняет роль терминала.
 * Грубо говоря ему передается устройство(пользователь), в котором прописано выполнение команд.
 * Передавая ему команды, мы абстрагируемся от того, как ему выполнить эту команду и есть ли она в его списке.
 */
public class CommandReader {

    Receiver receiver;
    Reader reader;

    public CommandReader(Receiver receiver, Reader reader) {
        this.receiver = receiver;
        this.reader = reader;
    }

    public void runInteractiveMode() throws IOException {
        String command = "";
        while (command != "exit") {
            command = reader.readCommand();
            receiver.runCommand(command);
        }
    }


}
