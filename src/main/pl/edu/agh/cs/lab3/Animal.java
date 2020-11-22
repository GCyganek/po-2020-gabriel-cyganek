package pl.edu.agh.cs.lab3;

import pl.edu.agh.cs.lab2.MapDirection;
import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab5.AbstractWorldMapElement;
import pl.edu.agh.cs.lab7.IPositionChangeObserver;
import pl.edu.agh.cs.lab7.IPositionChangedPublisher;

import java.util.LinkedList;

public class Animal extends AbstractWorldMapElement implements IPositionChangedPublisher{
    private final IWorldMap map;
    private MapDirection orientation = MapDirection.NORTH;
    private final LinkedList<IPositionChangeObserver> positionObserversList = new LinkedList<>();

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        if (!map.place(this)) throw new IllegalArgumentException(initialPosition +
                " position is already occupied, the animal has not been placed on the map.");
        positionObserversList.add((IPositionChangeObserver) map); // zakladam, ze mapa na ktorej ustawiamy zwierze bedzie chciala obserwowac jego ruchy
        priorityOnTheMap = 1;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return switch(orientation) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }

    public void move(MoveDirection direction) {
        switch(direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    Vector2d oldPosition = position;
                    position = newPosition;
                    positionChanged(oldPosition, newPosition);
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.substract(orientation.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    Vector2d oldPosition = position;
                    position = newPosition;
                    positionChanged(oldPosition, newPosition);
                }
            }
        }
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        positionObserversList.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        positionObserversList.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : positionObserversList) {
            observer.positionChanged(this, oldPosition, newPosition);
        }
    }
}
