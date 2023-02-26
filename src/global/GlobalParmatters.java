package global;

import client.commands.*;
import server.CollectionManager;
import server.Reader;

public class GlobalParmatters {
    public static Reader reader = new Reader();
    public static CollectionManager cM = new CollectionManager(System.getenv("KVOKKA"), reader);

    public static Command[] commands = new Command[]{new HelpCommand(), new AddCommand(),
            new InfoCommand(), new UpdateCommand(), new RemoveCommand(),
            new ClearCommand(), new SaveCommand(), new ExecuteScriptCommand(), new ExitCommand(),
            new RemoveFirstCommand(), new AddIfMinCommand(), new RemoveGreaterCommand(),
            new SumExpelledCommand()};
}
