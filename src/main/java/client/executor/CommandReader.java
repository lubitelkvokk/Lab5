package client.executor;




import client.*;
import server.*;

import java.io.IOException;

//TODO Можно назвать класс как Terminal

/**
 * Суть этого класса заключается в том, что он выполняет роль терминала.
 * Грубо говоря ему передается устройство(пользователь), в котором прописано выполнение команд.
 * Передавая ему команды, мы абстрагируемся от того, как ему выполнить эту команду и есть ли она в его списке.
 */
public class CommandReader {

    Receiver receiver;
    IReader reader;

    public CommandReader(Receiver receiver, IReader reader) {
        this.receiver = receiver;
        this.reader = reader;
    }

    public void setReader(IReader reader){
        this.reader = reader;
    }

    public void runInteractiveMode() throws IOException {
        String command = "";
        try {
            while (command != "exit") {
                command = reader.readCommand();
                receiver.runCommand(command);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }



}
