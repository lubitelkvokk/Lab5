package main.java.client.commands;

import main.java.server.CollectionManager;


public class SumExpelledCommand implements Command{
    private CollectionManager collectionManager;
    private String name = "sum_of_should_be_expelled";

    public SumExpelledCommand() {
    }

    @Override
    public String getName() {
        return name;
    }
    public SumExpelledCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.sumExpelled();
    }
}
