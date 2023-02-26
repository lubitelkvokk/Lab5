package global;

import server.CollectionManager;
import server.Reader;

public class GlobalParmatters {
    public static Reader reader = new Reader();
    public static CollectionManager cM = new CollectionManager(System.getenv("KVOKKA"), reader);
}
