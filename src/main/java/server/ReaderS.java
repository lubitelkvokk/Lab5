package server;


import data.*;
import data.exceptions.IdException;
import data.exceptions.WorldBorderException;
import global.*;
import server.exeptions.InputException;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class ReaderS implements IReader {
    Scanner scanner;


    public ReaderS(Scanner scanner) {
        this.scanner = scanner;
    }

    public StudyGroup readElement() {
        StudyGroup studyGroup = new StudyGroup();
        try {
            studyGroup.setId(GlobalParmatters.cM.getFreeId());
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }

        scanner.nextLine();
        studyGroup.setName(readName("Введите название группы: "));
        readCoords(studyGroup);
        studyGroup.setCreationDate(ZonedDateTime.now());
        studyGroup.setStudentsCount(readSudentsCount());
        studyGroup.setShouldBeExpelled(readShouldBeExpelled());
        studyGroup.setTransferredStudents(readTransferredStudents());
        studyGroup.setSemesterEnum(readSemesterEnum());
        studyGroup.setGroupAdmin(readGroupAdmin());
        return studyGroup;
    }

    @Override
    public String readLine() throws IOException {
        return scanner.next();
    }

    @Override
    public void close() {
        scanner.close();
    }

    private Semester readSemesterEnum() {
        Semester semester = null;
        int x = 0;
        while (semester == null) {
            System.out.println("Введите один из предложенных семестров");
            System.out.println("\t SECOND(2),\n" +
                    "\t FOURTH(4),\n" +
                    "\t FIFTH(5),\n" +
                    "\t SEVENTH(7),\n" +
                    "\t EIGHTH(8);");
            String s = scanner.nextLine().trim();
            try {
                try {
                    x = Integer.parseInt(s);
                    semester = Semester.EIGHTH.getSemester(x);
                } catch (Exception e1) {
                    semester = Semester.valueOf(s);
                }
            } catch (Exception e) {
                System.out.println("Семестр введен неверно");
            }
        }
        return semester;
    }

    private long readSudentsCount() {
        long studentsCount = 0;
        while (studentsCount <= 0) {
            System.out.print("Введите количество студентов в группе: ");
            try {
                studentsCount = Long.parseLong(scanner.nextLine().trim());
                if (studentsCount <= 0) {
                    throw new InputException("Значение должно быть больше 0");
                }
            } catch (InputException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Значение должно быть числовым");
            }
        }
        return studentsCount;
    }

    private long readShouldBeExpelled() {
        long shouldBeExpelled = 0;
        while (shouldBeExpelled <= 0) {
            System.out.print("Введите количество студентов, которых следует исключить: ");
            try {
                shouldBeExpelled = Long.parseLong(scanner.nextLine().trim());
                if (shouldBeExpelled <= 0) {
                    throw new InputException("Значение должно быть больше 0");
                }
            } catch (InputException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Значение должно быть числовым");
            }
        }
        return shouldBeExpelled;
    }

    private int readTransferredStudents() {
        int transferredStudents = 0;
        boolean detect = false;
        while (transferredStudents <= 0) {
            System.out.print("Введите количество переведенных студентов: ");
            try {
                transferredStudents = Integer.parseInt(scanner.nextLine().trim());
                if (transferredStudents <= 0) {
                    throw new InputException("Значение должно быть больше 0");
                }
            } catch (InputException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Значение должно быть числовым");
            }
        }
        return transferredStudents;
    }

    private Person readGroupAdmin() {
        Person groupAdmin = new Person();
        groupAdmin.setName(readName("Введите имя лидера группы: "));
        // Рост
        while (groupAdmin.getHeight() == 0) {
            try {
                System.out.print("Введите рост лидера группы: ");
                long height = Long.parseLong(scanner.nextLine().trim());
                groupAdmin.setHeight(height);
            } catch (Exception e) {
                System.out.println("Значение должно быть числовым");
            }
        }
        //паспортные данные
        while (groupAdmin.getPassportID() == null) {
            short length = (short) (Math.random() * 15 + 6);
            String passportId = "";
            for (int x = 0; x < length; ++x) {
                passportId += (char) (Math.random() * 94 + 32);
            }
            try {
                if (GlobalParmatters.cM.checkPasportID(passportId)) {
                    groupAdmin.setPassportID(passportId);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        //цвет глаз
        while (groupAdmin.getEyeColor() == null) {
            System.out.println("Введите один из предложенных цвет глаз: ");
            System.out.println("\t RED,\n" +
                    "\t BLACK,\n" +
                    "\t BLUE,\n" +
                    "\t ORANGE");
            try {
                Color color = Color.valueOf(scanner.nextLine().trim());
                groupAdmin.setEyeColor(color);
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
        }

        //Данные локации
        Location location = new Location();
        boolean detector = false;
        long x = 0;
        while (!detector) {
            try {
                System.out.print("\t x: ");
                String res = scanner.nextLine().trim();
                if (res.trim().equals("")) {
                    break;
                }
                x = Long.parseLong(res);
                detector = true;
            } catch (Exception e) {
                System.out.println("Координаты должны быть целочисленными");
            }
        }
        location.setX(x);

        while (location.getY() == null) {
            System.out.print("\t y: ");
            try {
                String s = scanner.nextLine().trim();
                if (s.trim().equals("")) {
                    throw new InputException("Значение не может быть null");
                }
                Float y = Float.parseFloat(s);
                location.setY(y);
            } catch (InputException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Значение должно быть целочисленным");
            }
        }

        detector = false;
        int z = 0;
        while (!detector) {
            try {
                System.out.print("\t z: ");
                String res = scanner.nextLine().trim();
                if (res.trim().equals("")) {
                    break;
                }
                z = Integer.parseInt(res);
                detector = true;
            } catch (Exception e) {
                System.out.println("Координаты должны быть целочисленными");
            }
        }
        location.setZ(z);
        System.out.print("Введите название локации: ");
        String name = scanner.nextLine();
        if (name.trim().equals("")) {
            name = null;
        }
        location.setName(name);
        groupAdmin.setLocation(location);
        return groupAdmin;
    }

    private void readCoords(StudyGroup studyGroup) {
        System.out.println("Введите координаты группы: ");
        Coordinates coordinates = new Coordinates();

        boolean detector = false;
        while (!detector) {
            try {
                System.out.print("\t x: ");
                String res = scanner.nextLine();
                if (res.trim().equals("")) {
                    detector = true;
                    break;
                }
                long x = Long.parseLong(res.trim());
                coordinates.setX(x);
                detector = true;
            } catch (WorldBorderException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Координаты должны быть целочисленными");
            }
        }
        while (coordinates.getY() == 0) {
            try {
                System.out.print("\t y: ");
                long y = Long.parseLong(scanner.nextLine().trim());
                coordinates.setY(y);
            } catch (WorldBorderException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Координаты должны быть целочисленными");
            }
        }
        studyGroup.setCoordinates(coordinates);
    }

    public String readName(String msg) {
        String name = "";
        while (name.equals("")) {
            try {
                System.out.print(msg);
                name = scanner.nextLine();
                if (name.trim().equals("")) {
                    throw new InputException("Имя не может быть пустой строкой");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return name;
    }

    @Override
    public String readCommand() {
        return scanner.next();
    }
}
