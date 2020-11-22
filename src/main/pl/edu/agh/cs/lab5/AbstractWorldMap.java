package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab4.MapVisualiser;
import pl.edu.agh.cs.lab7.IPositionChangeObserver;
import pl.edu.agh.cs.lab7.MapBoundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected static final Vector2d lowerLeft = new Vector2d(0, 0);
    private final MapVisualiser thisMapVisualiser = new MapVisualiser(this);
    protected HashMap<Vector2d, Animal> animalHashMap = new HashMap<>();
    protected MapBoundary mapBoundary = new MapBoundary();

    @Override
    public String toString() {
        return thisMapVisualiser.draw(mapBoundary.lowerLeft(), mapBoundary.upperRight());
    }

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public boolean place(Animal animal){
        if (!canMoveTo(animal.getPosition())) {
            return false;
        } else {
            animalHashMap.put(animal.getPosition(), animal);
            animal.addObserver(mapBoundary);
            mapBoundary.addElement(animal);
            return true;
        }
    }

    @Override
    public void run(List<MoveDirection> directions) {
        ArrayList<Animal> animalList = new ArrayList<>(animalHashMap.values());
        int animalIndex = 0;

        for (MoveDirection direction : directions) {
            animalList.get(animalIndex).move(direction);
            animalIndex = (animalIndex + 1) % animalList.size();
            //System.out.println(this); //debug zeby sprawdzic, czy MapBoundary dziala
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animalHashMap.containsKey(position);
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        return Optional.ofNullable(animalHashMap.get(position));
    }

    @Override
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        animalHashMap.remove(oldPosition);
        animalHashMap.put(newPosition, (Animal) movedElement);
    }
}
