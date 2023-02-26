package client.commands;

import server.CollectionManager;

public interface Command {

    void execute();

    void setCollectionManager(CollectionManager collectionManager);

    String getName();

    ;
}
