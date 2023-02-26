package data;

import data.exceptions.WorldBorderException;

public class Coordinates {
    private long x;
    private float y; //Значение поля должно быть больше -828

    public Coordinates(long x, float y) throws WorldBorderException {
        if (y < -909 || x < -799){
            throw new WorldBorderException();
    }
        this.x = x;

        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}