package main.java;



import client.Receiver;
import client.executor.CommandReader;
import global.GlobalParmatters;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Receiver user = new Receiver(GlobalParmatters.commands, GlobalParmatters.cM);
        CommandReader commandReader = new CommandReader(user, GlobalParmatters.reader);
        commandReader.runInteractiveMode();
//        while (true){
//            System.out.println(scanner.next());
//            System.out.println(scanner.nextLine());
//        }


//        Receiver user = new Receiver(GlobalParmatters.commands, GlobalParmatters.cM);
//        CommandReader commandReader = new CommandReader(user, GlobalParmatters.reader);
//        commandReader.runInteractiveMode();


//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(StudyGroup.class, new CustomConverter());
//        Gson gson = builder.create();
////        System.out.println(gson.toJson(studyGroup));
//        StudyGroup studyGroup = gson.fromJson("{\"id\":3,\"name\":\"PopIT\",\"coordinates\":{\"x\":25,\"y\":10.0},\"creationDate\":\"2023-02-23T18:21:35.597551+03:00[Europe/Moscow]\",\"groupAdmin\":{\"name\":\"Andrew\"},\"studentsCount\":7,\"shouldBeExpelled\":8,\"transferredStudents\":\"6\",\"semesterEnum\":\"SECOND\"}",
//                StudyGroup.class);
//        System.out.println(studyGroup);

    }
}