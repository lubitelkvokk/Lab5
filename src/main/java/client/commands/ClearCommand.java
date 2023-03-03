package main.java.client.commands;

import main.java.server.CollectionManager;


public class ClearCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "clear";

    public ClearCommand() {
    }

    @Override
    public String getName() {
        return name;
    }
    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.clear();
    }
}
