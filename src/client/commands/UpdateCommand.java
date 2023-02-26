package client.commands;

import server.CollectionManager;

public class UpdateCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "update";

    public UpdateCommand() {

    }

    @Override
    public String getName() {
        return name;
    }
    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.update();
    }
}
