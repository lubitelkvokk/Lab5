package client.commands;


import server.CollectionManager;

public class AddCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "add";
    public AddCommand() {
    }

    public AddCommand(CollectionManager collectionManager) {
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
        collectionManager.add();
    }

}
