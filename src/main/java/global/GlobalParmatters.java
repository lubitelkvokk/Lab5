package global;



import client.commands.*;
import server.*;

import java.util.Scanner;


public class GlobalParmatters {
    public static IReader reader = new ReaderS(new Scanner(System.in));

    public static CollectionManager cM = new CollectionManager(System.getenv("KVOKKA"), reader);

    public static Command[] commands = new Command[]{new HelpCommand(), new AddCommand(),
            new InfoCommand(), new UpdateCommand(), new RemoveCommand(),
            new ClearCommand(), new SaveCommand(), new ExecuteScriptCommand(), new ExitCommand(),
            new RemoveFirstCommand(), new AddIfMinCommand(), new RemoveGreaterCommand(),
            new SumExpelledCommand(), new AverageOfShouldBeExpelled(),
            new MinByNameCommand(), new ShowCommand()};
}
