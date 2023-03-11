package client.commands;

import server.CollectionManager;


public class ExecuteScriptCommand implements Command {

    private CollectionManager collectionManager;
    private String name = "execute_script";

    public ExecuteScriptCommand() {
    }

    public ExecuteScriptCommand(CollectionManager collectionManager) {
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
        collectionManager.executeScript();
    }
}
