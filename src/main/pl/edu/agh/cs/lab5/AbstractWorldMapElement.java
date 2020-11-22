package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.Vector2d;

public abstract class AbstractWorldMapElement implements IMapElement{
    protected Vector2d position;
    protected int priorityOnTheMap;

    public int getPriorityOnTheMap() { return priorityOnTheMap; }

    public Vector2d getPosition() {
        return position;
    }

}
