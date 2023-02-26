package data;

import server.exeptions.FormatElementException;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Integer height; //Поле может быть null, Значение поля должно быть больше 0
    private Integer weight; //Поле не может быть null, Значение поля должно быть больше 0
    private String passportID; //Длина строки должна быть не меньше 10, Строка не может быть пустой, Поле не может быть null

    private Location location;
    public Person() {

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setPassportID(String passportID) throws FormatElementException {
        if (passportID.length() >= 21 && passportID.length() <= 6) {
            throw new FormatElementException("Паспортные данные введены некорректно");
        }
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                ", location=" + location +
                '}';
    }
}