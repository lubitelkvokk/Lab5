package client.executor;

import client.commands.Receiver;
import server.Reader;

import java.io.BufferedInputStream;
import java.io.IOException;

//TODO Можно назвать класс как Termonal

/**
 * Суть этого класса заключается в том, что он выполняет роль терминала.
 * Грубо говоря ему передается устройство(пользователь), в котором прописано выполнение команд.
 * Передавая ему команды, мы абстрагируемся от того, как ему выполнить эту команду и есть ли она в его списке.
 */
public class Executor {

    Receiver receiver;
    Reader reader;

    public Executor(Receiver receiver, Reader reader) {
        this.receiver = receiver;
        this.reader = reader;
    }

    public void runInteractiveMode() throws IOException {
        String command = "";
        while (command != "exit") {
            command = reader.readCommand();
            receiver.searchCommand(command);
        }
    }


}
