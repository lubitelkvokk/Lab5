package client.commands;

import server.CollectionManager;

public class AddIfMinCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "add_if_min";

    public AddIfMinCommand() {
    }

    @Override
    public String getName() {
        return name;
    }

    public AddIfMinCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.addIfMin();
    }
}
