package client.commands;


import server.CollectionManager;

public class SaveCommand implements Command {


    private CollectionManager collectionManager;
    private String name = "save";

    public SaveCommand() {
    }

    public SaveCommand(CollectionManager collectionManager) {
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
        collectionManager.save();
    }
}
