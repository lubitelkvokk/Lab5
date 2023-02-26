package server;

import client.Receiver;
import client.executor.CommandReader;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import data.IdContainer;
import data.StudyGroup;
import com.google.gson.Gson;
import data.exceptions.CrossingIdException;
import data.exceptions.CrossingPasportIDException;
import data.exceptions.IdException;
import data.exceptions.WorldBorderException;
import global.GlobalParmatters;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;

public class CollectionManager {

    /**
     * Поле списка учебных групп
     */
    LinkedList<StudyGroup> studyGroups;
    Reader reader;
    /**
     * Поле сериализатора и десериализатора json
     */

    private CollectionChecker collectionChecker = new CollectionChecker(this);
    Gson gson;

    {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StudyGroup.class, new CustomConverter());
        gson = builder.create();
    }



    /*
    Сделать метод для проверки всех ошибок и загрузки в коллекцию объектов /load + придумать название метода
     */

    /**
     * Конструктор - создание нового объекта с указанием пути до файла, данные из которого должны быть преобразованы
     * в список объектов StudyGroup
     *
     * @param path - путь к файлу
     */
    public CollectionManager(String path, Reader reader) {
        try {
            String s = Reader.readFromFile(path);
            Type t = new TypeToken<LinkedList<StudyGroup>>() {
            }.getType();
            studyGroups = (LinkedList<StudyGroup>) gson.fromJson(s, t); //TODO Как происходит каст? Почему без бубна TreeMap? https://www.baeldung.com/gson-list
            for (StudyGroup group : studyGroups) {
                IdContainer.container.add(group.getId());
            }
            if (IdContainer.container.size() != studyGroups.size()) {
                throw new CrossingIdException("В исходном файле найдено пересечение id групп");
            }
            this.reader = reader;

//            for (StudyGroup stG : studyGroups) {
//                IdContainer.container.add(stG.getId());
//            }
//            if (IdContainer.container.size() != studyGroups.size()) {
//                throw new CrossingIdException("Пересечение id групп в файле");
//            }
            if (studyGroups == null) {
                System.out.println("Файл пустой");
                System.exit(1);
            }

            System.out.println("Коллекция успешно обновлена");
        } catch (FileNotFoundException e) {
            System.out.println("Необходимо передать путь через переменную окружения KVOKKA");
            System.exit(1);
        } catch (JsonSyntaxException e) {
            System.out.println("Неверный формат данных в файле");
            System.exit(1);
        } catch (SecurityException e) {
            System.out.println("Ошибка прав доступа к файлу");
            System.exit(1);
        } catch (CrossingIdException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            System.out.println(e.getCause().getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void help() {
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add : добавить новый элемент в коллекцию\n" +
                "update id : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_first : удалить первый элемент из коллекции\n" +
                "add_if_min  : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "remove_greater  : удалить из коллекции все элементы, превышающие заданный\n" +
                "sum_of_should_be_expelled : вывести сумму значений поля shouldBeExpelled для всех элементов коллекции\n" +
                "average_of_should_be_expelled : вывести среднее значение поля shouldBeExpelled для всех элементов коллекции\n" +
                "min_by_name : вывести любой объект из коллекции, значение поля name которого является минимальным");
    }


    public void add() {
        try {
//            String json = reader.readElement();
//            StudyGroup obj = gson.fromJson(json, StudyGroup.class);
            StudyGroup obj = reader.readElement();
            System.out.println(obj.getId());

            studyGroups.add(obj);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public void info() {
        System.out.println("Размер коллекции:" + studyGroups.size() + "\n"
                + "Тип коллекции: " + studyGroups.getClass() + "\n"
                + studyGroups);
    }

    public void update() {
        try {
            int id = Integer.parseInt(reader.readLine().trim());
            for (int x = 0; x < studyGroups.size(); ++x) {
                if (studyGroups.get(x).getId().equals(id)) {

                    studyGroups.set(x, reader.readElement());
                }
            }
        } catch (Exception e) {
            System.out.println("Неверный формат ввода id");
        }

    }

    public boolean checkId(Integer id) throws IdException, CrossingIdException {
        return collectionChecker.checkId(id);
    }

    public boolean checkPasportID(String msg) throws CrossingPasportIDException {
        return collectionChecker.checkPasportID(msg);
    }

    public LinkedList<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    public void remove() {
        try {
            int id = Integer.parseInt(reader.readLine().trim());
            for (int x = 0; x < studyGroups.size(); ++x) {
                if (studyGroups.get(x).getId().equals(id)) {
                    studyGroups.remove(x);
                    System.out.println("Элемент коллекции успешно удален!");
                }
            }
        } catch (Exception e) {
            System.out.println("Неверный формат ввода id");
        }
    }

    public void clear() {
        studyGroups = new LinkedList<>();
        System.out.println("Коллекция успешно очищена!");
    }

    public void save() {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter("C:\\Users\\Alex\\IdeaProjects\\Lab5\\result.json"));
            bf.write(gson.toJson(studyGroups));
            bf.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void executeScript() {
        try {
            reader.readScript(reader.readLine());


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void exit() {
        System.out.println("-------END--------");
        try {
            reader.bf.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //System.exit(1);
    }

    public void removeFirst() {
        if (studyGroups.size() == 0) {
            System.out.println("Коллекция уже пустая");
        } else {
            studyGroups.remove(0);
        }

    }

    public void addIfMin() {
        try {
            StudyGroup studyGroup = reader.readElement();
            if (studyGroup.compareTo(Collections.min(studyGroups)) < 0) {
                studyGroups.add(studyGroup);
            } else {
                System.out.println("Этот элемент не минимальный");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void remove_greater() {
        try {
            StudyGroup studyGroup = reader.readElement();
            for (int x = 0; x < studyGroups.size(); ++x) {
                if (studyGroups.get(x).compareTo(studyGroup) > 0) {
                    studyGroups.remove(x);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sumExpelled() {
        long sum = 0;
        for (StudyGroup x : studyGroups) {
            sum += x.getShouldBeExpelled();
        }
        System.out.println(sum);
    }
}
