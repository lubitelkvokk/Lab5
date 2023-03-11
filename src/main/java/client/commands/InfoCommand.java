package client.commands;

import com.sun.source.tree.IfTree;
import server.CollectionManager;


public class InfoCommand implements Command {
    private CollectionManager collectionManager;
    private String name = "info";

    public InfoCommand() {

    }

    @Override
    public String getName() {
        return name;
    }
    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.info();
    }
}
