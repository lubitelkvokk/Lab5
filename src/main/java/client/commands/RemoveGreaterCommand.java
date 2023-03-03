package main.java.client.commands;

import main.java.server.CollectionManager;


public class RemoveGreaterCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "remove_greater";

    public RemoveGreaterCommand() {
    }

    @Override
    public String getName() {
        return name;
    }

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.remove_greater();
    }
}
