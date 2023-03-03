package main.java.client.commands;

import main.java.server.CollectionManager;


public class MinByNameCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "min_by_name";

    public void HelpCommand() {
    }

    public void HelpCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.minByName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}
