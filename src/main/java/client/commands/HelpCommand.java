package main.java.client.commands;

import main.java.server.CollectionManager;


public class HelpCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "help";
    public HelpCommand() {
    }

    public HelpCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.help();
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
