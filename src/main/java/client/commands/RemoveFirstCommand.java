package main.java.client.commands;

import main.java.server.CollectionManager;


public class RemoveFirstCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "remove_first";

    public RemoveFirstCommand() {
    }

    @Override
    public String getName() {
        return name;
    }

    public RemoveFirstCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.removeFirst();
    }

}
