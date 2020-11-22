package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.Vector2d;

public class Grass extends AbstractWorldMapElement{

    public Grass(Vector2d position) {
        this.position = position;
        priorityOnTheMap = 0;
    }

    @Override
    public String toString() {
        return "*";
    }
}
