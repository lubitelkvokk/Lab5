package main.java.client.commands;

import main.java.server.CollectionManager;


public interface Command {

    void execute();

    void setCollectionManager(CollectionManager collectionManager);

    String getName();

    ;
}
