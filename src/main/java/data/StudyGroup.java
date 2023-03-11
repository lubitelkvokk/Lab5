package data;


import data.exceptions.*;

import java.time.ZonedDateTime;

public class StudyGroup implements Comparable<StudyGroup> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount; //Значение поля должно быть больше 0
    private long shouldBeExpelled; //Значение поля должно быть больше 0
    private int transferredStudents; //Значение поля должно быть больше 0

    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null

    public StudyGroup(Integer id, String name,
                      Coordinates coordinates,
                      ZonedDateTime creationDate,
                      long studentsCount, long shouldBeExpelled,
                      int transferredStudents,
                      Semester semesterEnum, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.shouldBeExpelled = shouldBeExpelled;
        this.transferredStudents = transferredStudents;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public StudyGroup() {

    }

    public void setId(Integer id) throws IdException {
        if (id <= 0){
            throw new IdException();
        }
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setStudentsCount(long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setShouldBeExpelled(long shouldBeExpelled) {
        this.shouldBeExpelled = shouldBeExpelled;
    }

    public void setTransferredStudents(int transferredStudents) {
        this.transferredStudents = transferredStudents;
    }

    public int getTransferredStudents() {
        return transferredStudents;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public long getShouldBeExpelled() {
        return shouldBeExpelled;
    }


    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }


    @Override
    public int compareTo(StudyGroup o) {
        return this.id - o.id;
    }


    @Override
    public String toString() {
        return "StudyGroup{" +
                "\n\t id=" + id +
                ",\n\t name='" + name + '\'' +
                ",\n\t coordinates=" + coordinates +
                ",\n\t creationDate=" + creationDate +
                ",\n\t studentsCount=" + studentsCount +
                ",\n\t shouldBeExpelled=" + shouldBeExpelled +
                ",\n\t transferredStudents=" + transferredStudents +
                ",\n\t semesterEnum=" + semesterEnum +
                ",\n\t groupAdmin=" + groupAdmin +
                "\n\t}\n";
    }
}

