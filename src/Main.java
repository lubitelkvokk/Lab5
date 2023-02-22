
import client.commands.AddCommand;
import client.commands.HelpCommand;
import client.commands.InfoCommand;
import client.commands.Receiver;
import client.executor.Executor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.StudyGroup;
import server.CollectionManager;
import server.Reader;
import server.ZonedDateTimeSerializer;

import java.io.*;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) throws IOException {
//        Reader reader = new Reader();
//        CollectionManager cM = new CollectionManager(System.getenv("KVOKKA"), reader);
//        Receiver user = new Receiver(new HelpCommand(cM), new AddCommand(cM), new InfoCommand(cM));
//        Executor executor = new Executor(user, reader);
//        executor.runInteractiveMode();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(ZonedDateTime.class, ZonedDateTime.now())
                .create();

        StudyGroup studyGroup = new StudyGroup();

    }
}