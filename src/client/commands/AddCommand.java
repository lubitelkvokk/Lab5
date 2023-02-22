package client.commands;

import server.CollectionManager;

public class AddCommand implements Command{
    private CollectionManager collectionManager;


    public AddCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.add();
    }

}
