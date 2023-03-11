package client.commands;

import server.CollectionManager;

public class AverageOfShouldBeExpelled implements Command {
    private CollectionManager collectionManager;
    private String name = "average_of_should_be_expelled";

    public AverageOfShouldBeExpelled() {
    }

    @Override
    public String getName() {
        return name;
    }

    public AverageOfShouldBeExpelled(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.averageOfShouldBeExpelled();
    }
}
