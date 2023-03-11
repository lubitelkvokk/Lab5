package data;


import server.exeptions.*;

public class Person {

    private String name; //Поле не может быть null, Строка не может быть пустой
    private long height; //Значение поля должно быть больше 0
    private String passportID; //Значение этого поля должно быть уникальным, Длина строки не должна быть больше 21, Длина строки должна быть не меньше 6, Поле не может быть null

    private Color eyeColor; //Поле не может быть null
    private Location location; //Поле не может быть null

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setHeight(long height) throws InputException {
        if (height < 0) {
            throw new InputException("Значение должно быть больше 0");
        }
        this.height = height;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

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

    public long getHeight() {
        return height;
    }


    public String getPassportID() {
        return passportID;
    }

    public void setName(String name) {
        this.name = name;
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
                ", passportID='" + passportID + '\'' +
                ", eyeColor=" + eyeColor +
                ", location=" + location +
                '}';
    }
}