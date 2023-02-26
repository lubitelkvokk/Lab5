package client;

import client.commands.*;
import server.CollectionManager;

import java.util.LinkedList;

//Invoker

/**
 * Класс, получающий в распоряжение команды, которые он может выполнять.
 */
public class Receiver {
    /**
     * Команды, вводимые пользователем
     */

    private Command[] commands;

    /**
     * Конструктор, в который передаются команды, доступные пользователю
     *
     * @param commands
     */
    public Receiver(Command[] commands) {
        this.commands = commands;
    }

    public Receiver(Command[] commands, CollectionManager cM) {
        for (Command command : commands) {
            command.setCollectionManager(cM);
        }
        this.commands = commands;

    }

    /**
     * Получает команду, ищет ее в списке имеющихся
     *
     * @param msg - команда на выполнение
     */

    public void runCommand(String msg) {
        Command command = searchCommand(msg.trim());
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Команда введена неверно");
        }
    }

    public Command searchCommand(String msg) {
        for (Command command : commands) {
            if (command.getName().equals(msg)) {
                return command;
            }
        }
        return null;
    }

    public void exit() {
        System.out.println("-------END--------");
        System.exit(1);
    }
}
