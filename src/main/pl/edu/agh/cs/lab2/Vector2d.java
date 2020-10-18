package pl.edu.agh.cs.lab2;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Vector2d vector2d = (Vector2d) other;
        return x == vector2d.x &&
                y == vector2d.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean precedes(Vector2d other) {
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return x >= other.x && y >= other.y;
    }

    public Vector2d upperRight(Vector2d other) {
        int newX, newY;

        newX = Math.max(x, other.x);
        newY = Math.max(y, other.y);

        return new Vector2d(newX, newY);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int newX, newY;

        newX = Math.min(x, other.x);
        newY = Math.min(y, other.y);

        return new Vector2d(newX, newY);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d substract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    public Vector2d opposite() {
        return new Vector2d(-x, -y);
    }

}
