package client.commands;

import server.CollectionManager;

public class HelpCommand implements Command {
    private CollectionManager collectionManager;

    public HelpCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.help();
    }

}
