package main.java.client.commands;

import main.java.server.CollectionManager;


public class RemoveCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "remove";

    public RemoveCommand() {

    }

    @Override
    public String getName() {
        return name;
    }
    public RemoveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.remove();
    }
}