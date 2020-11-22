package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab7.MapBoundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;


public class GrassField extends AbstractWorldMap {
    private final int grassFields;
    private final HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();
    private final Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public GrassField(int grassFields) {
        this.grassFields = grassFields;
        generateGrassFields();
        mapBoundary = new MapBoundary(new ArrayList<>(grassHashMap.values()));
    }

    private void generateGrassFields() {
        while (grassHashMap.values().size() != grassFields) {
            int x = getRandomNumber((int) (Math.sqrt(grassFields * 10) + 1));
            int y = getRandomNumber((int) (Math.sqrt(grassFields * 10) + 1));

            placeGrass(new Grass(new Vector2d(x, y)));
        }
    }

    private int getRandomNumber(int max) {
        return (int) (Math.random() * max);
    }


    public void placeGrass(Grass grass) {
        if (!grassHashMap.containsKey(grass.getPosition())) {
            grassHashMap.put(grass.getPosition(), grass);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(lowerLeft) && position.precedes(upperRight)) {
            return !super.isOccupied(position);
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || isOccupiedByGrass(position);
    }

    public boolean isOccupiedByGrass(Vector2d position) {
        return grassHashMap.containsKey(position);
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if (super.objectAt(position).equals(Optional.empty())) {
            return Optional.ofNullable(grassHashMap.get(position));
        }
        else return Optional.of(animalHashMap.get(position));
    }
}
