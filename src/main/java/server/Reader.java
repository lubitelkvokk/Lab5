package server;





import data.*;
import data.exceptions.*;
import global.*;
import server.exeptions.*;

import java.io.*;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Scanner;


public class Reader implements IReader {
    BufferedInputStream bf;


    public Reader(String path) throws FileNotFoundException {
        bf = new BufferedInputStream(new FileInputStream(new File(path)));
    }

    /**
     * @param path путь до файла с данными
     * @return содержимое файла
     * @throws IOException - ошибка, связанная с существованием, доступом и содержимом файла
     */
    public static String readFromFile(String path) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
        return new String(bufferedInputStream.readAllBytes(), "UTF-8");
    }


    public String readLine() throws IOException {
        char point = (char) bf.read();
        String s = "";
        while (point == 32 || point == 10) { // код символа перехода на новую строку
            point = (char) bf.read();
            s += point;
        }

        while (point != 10 && point != 65535) { // код символа перехода на новую строку
            s += point;
            point = (char) bf.read();

        }
        if (point == 65535) {
            System.exit(1);
        }
        return s.trim();
    }

    @Override
    public void close() throws IOException {
        bf.close();
    }


    public StudyGroup readElement() {
//        String str = readLine().trim();

//        if (!str.equals("{")) {
//            throw new FormatElementException("Неверный формат ввода элемента");
//        }

        StudyGroup studyGroup = new StudyGroup();
        try {
            studyGroup.setId(GlobalParmatters.cM.getFreeId());
        } catch (IdException e) {

        }

        //name
        readName(studyGroup);
        //coords
        readCoordinates(studyGroup);
        //creation date
        studyGroup.setCreationDate(ZonedDateTime.now());
        //group admin
        readGroupAdmin(studyGroup);
        //studentCount
        readStudentCount(studyGroup);

        while (studyGroup.getShouldBeExpelled() == 0) {
            try {
                Long shouldBeExpelled = Long.valueOf(readLine().trim());
                if (shouldBeExpelled <= 0) {
                    throw new FormatElementException("Количество исключенных должно быть больше 0");
                }
                if (shouldBeExpelled > studyGroup.getStudentsCount()) {
                    throw new FormatElementException("Количество исключенных не должно превышать число студентов");
                }
                studyGroup.setShouldBeExpelled(shouldBeExpelled);
            } catch (Exception e) {
                // System.out.println(e.getMessage());
            }
        }

        while (studyGroup.getTransferredStudents() == 0) {
            try {
                int transferredStudents = Integer.parseInt(readLine().trim());

                if (transferredStudents <= 0) {
                    throw new FormatElementException("Количество переведенных должно быть больше 0");
                }
                studyGroup.setTransferredStudents(transferredStudents);
            } catch (Exception e) {
                // System.out.println(e.getMessage());
            }
        }

        boolean checker = false;
        while (!checker) {
            try {
                for (Field s : Semester.class.getDeclaredFields()) {
                    // System.out.print(s.getName() + " ");
                }
                String s = readLine().trim();
                if (s.equals("")) {
                    checker = true;
                }
                Semester semester = Semester.valueOf(s);
                if (semester == null && !s.equals("")) {
                    throw new FormatElementException("Семестр написан некорректно");
                }
                studyGroup.setSemesterEnum(semester);
                checker = true;
            } catch (Exception e) {
                // System.out.println(e.getMessage());
            }
        }

//
//        // System.out.print("Введите название группы: ");
//        studyGroup.setName(readLine().trim());
        return studyGroup;
    }

    public String readCommand() throws IOException {

        char point = 0;
        String str = "";


        while (point != 10 && point != 32 && point != 65535 && !str.equals("exit")) {
            point = (char) bf.read();
            str += point;
        }
        return str.trim();
    }

    public void readId(StudyGroup studyGroup) {
        while (studyGroup.getId() == null) {
            try {
                String s = readLine().trim();
                Integer id = Integer.valueOf(s);
                if (GlobalParmatters.cM.checkId(id)) {

                    studyGroup.setId(id);
                }
            } catch (IdException e) {
                // System.out.println();
                // System.out.print("Введите id заново: ");
            } catch (CrossingIdException e) {
                // // System.out.println(e.getMessage());
                // System.out.print("Введите id заново: ");
            } catch (Exception e) {
                // System.out.println("id должен быть числовым");
                // System.out.print("Введите id заново: ");
            }
        }

    }

    public void readName(StudyGroup studyGroup) {
        while (studyGroup.getName() == null) {
            try {
                String name = readLine();
                if (name == null || name.trim().equals("")) {
                    throw new FormatElementException("Введена пустая строка");
                }
                studyGroup.setName(name);
            } catch (Exception e) {
                // System.out.println(e.getMessage());
            }
        }
    }

    public void readCoordinates(StudyGroup studyGroup) {
        // System.out.println("Введите координаты: ");
        while (studyGroup.getCoordinates() == null) {
            try {
                // System.out.print("\t x: ");
                long x = Long.parseLong(readLine().trim());
                // System.out.print("\t y: ");
                float y = Float.parseFloat(readLine().trim());
                studyGroup.setCoordinates(new Coordinates(x, y));
            } catch (Exception e) {
                // System.out.println("Введите другие значения координат: ");
            }
        }
    }

    public void readGroupAdmin(StudyGroup studyGroup) {
        // System.out.print("Введите персональные данные лидера группы: ");
        Person groupAdmin = new Person();

        while (groupAdmin.getName() == null) {
            try {
                // System.out.print("Имя: ");

                String name = readLine();
                if (name == null) {
                    throw new FormatElementException("Введена пустая строка");
                }
                groupAdmin.setName(name);
            } catch (Exception e) {
                // System.out.println(e.getMessage());
            }
        }
        while (groupAdmin.getHeight() == 0) {
            try {
                // System.out.print("Рост: ");

                Long height = Long.valueOf(readLine());
                if (height == 0) {
                    throw new FormatElementException("Введена пустая строка");
                }
                groupAdmin.setHeight(height);
            } catch (Exception e) {
                // System.out.println(e.getMessage());
            }
        }


        while (groupAdmin.getPassportID() == null) {
            try {
                // System.out.print("Паспортные данные: ");

                String pasportID = readLine();

                if (pasportID == null) {
                    throw new FormatElementException("Введена пустая строка");
                }
                if (GlobalParmatters.cM.checkPasportID(pasportID)) {
                    groupAdmin.setPassportID(pasportID);
                }

            } catch (Exception e) {
                // System.out.println(e.getMessage());
            }
        }
        readLocation(groupAdmin);
        studyGroup.setGroupAdmin(groupAdmin);
    }


    public void readLocation(Person person) {
        boolean checker = false;
        Location location = new Location();
        // System.out.println("Введите данные локации:");
        while (!checker) {
            try {
                // System.out.print("\t x: ");

                long x = Long.parseLong(readLine().trim());
                location.setX(x);
                checker = true;
            } catch (Exception e) {
                // System.out.print("Введите другие значения координат: ");
            }
        }

        while (location.getY() == null) {
            try {
                // System.out.print("\t y: ");

                float y = Float.parseFloat(readLine().trim());
                location.setY(y);
            } catch (Exception e) {
                // System.out.print("Введите другие значения координат: ");
            }
        }

        checker = false;
        while (!checker) {
            try {
                // System.out.print("\t z: ");

                int z = Integer.parseInt(readLine().trim());
                location.setZ(z);
                checker = true;
            } catch (Exception e) {
                // System.out.print("Введите другие значения координат: ");
            }
        }
        checker = false;
        while (!checker) {
            try {
                // System.out.print("Имя: ");

                String name = readLine();
                if (name.length() > 797) {
                    throw new FormatElementException("Название превышает разрешенный размер");
                }
                location.setName(name);
                checker = true;
            } catch (Exception e) {
                // System.out.println(e.getMessage());
            }
        }
        person.setLocation(location);
    }

    public void readStudentCount(StudyGroup studyGroup) {
        while (studyGroup.getStudentsCount() <= 0) {
            try {
                // System.out.print("Введите количество участников группы: ");

                long studentsCount = Long.parseLong(readLine().trim());

                if (studentsCount <= 0) {
                    throw new FormatElementException("Количество участников должно быть больше 0");
                }
                studyGroup.setStudentsCount(studentsCount);

            } catch (Exception e) {
                // System.out.println(e.getMessage());
            }
        }
    }
}
