package server;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import data.IdContainer;
import data.StudyGroup;
import com.google.gson.Gson;
import data.exceptions.CrossingIdException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
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
    Gson gson = new Gson();


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
            studyGroups = (LinkedList<StudyGroup>) new Gson().fromJson(s, t); //TODO Как происходит каст? Почему без бубна TreeMap? https://www.baeldung.com/gson-list
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void help() {
        System.out.println("HELP информация");
    }


    public void add() {
        try {
            StudyGroup obj = gson.fromJson(reader.readElement(), StudyGroup.class);
            if (IdContainer.container.contains(obj.getId())) {
                System.out.println("ID_ERROR - группа с таким id уже присутствует в коллекции. Введите другие параметры");
            } else {
                studyGroups.add(obj);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void info() {
        System.out.println("Размер коллекции:" + studyGroups.size() + "\n"
                + "Тип коллекции: " + studyGroups.getClass());
    }
}
