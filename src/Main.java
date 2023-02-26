
import client.commands.*;
import client.Receiver;
import client.executor.CommandReader;
import global.GlobalParmatters;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Command[] commands = new Command[]{new HelpCommand(), new AddCommand(),
                new InfoCommand(), new UpdateCommand(), new RemoveCommand(),
                new ClearCommand(), new SaveCommand()};

        Receiver user = new Receiver(commands, GlobalParmatters.cM);
        CommandReader commandReader = new CommandReader(user, GlobalParmatters.reader);
        commandReader.runInteractiveMode();


//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(StudyGroup.class, new CustomConverter());
//        Gson gson = builder.create();
////        System.out.println(gson.toJson(studyGroup));
//        StudyGroup studyGroup = gson.fromJson("{\"id\":3,\"name\":\"PopIT\",\"coordinates\":{\"x\":25,\"y\":10.0},\"creationDate\":\"2023-02-23T18:21:35.597551+03:00[Europe/Moscow]\",\"groupAdmin\":{\"name\":\"Andrew\"},\"studentsCount\":7,\"shouldBeExpelled\":8,\"transferredStudents\":\"6\",\"semesterEnum\":\"SECOND\"}",
//                StudyGroup.class);
//        System.out.println(studyGroup);

    }
}