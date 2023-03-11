package client.commands;


import server.CollectionManager;

public class ShowCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "show";

    public ShowCommand() {
    }

    public ShowCommand(CollectionManager collectionManager) {
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
        collectionManager.show();
    }
}
