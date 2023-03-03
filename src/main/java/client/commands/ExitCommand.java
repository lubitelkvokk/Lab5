package main.java.client.commands;

import main.java.server.CollectionManager;


public class ExitCommand implements Command{
    private CollectionManager collectionManager;
    private String name = "exit";
    public ExitCommand() {
    }

    public ExitCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public void execute() {
        collectionManager.exit();
    }
}
