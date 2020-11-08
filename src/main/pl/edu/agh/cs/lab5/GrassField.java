package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;

import java.util.ArrayList;
import java.util.Optional;

import java.util.concurrent.ThreadLocalRandom;


public class GrassField extends AbstractWorldMap{
    private final int grassFields;
    private final ArrayList<Grass> grassList = new ArrayList<>();
    private final Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public GrassField(int grassFields) {
        this.grassFields = grassFields;
        generateGrassFields();
    }

    private void generateGrassFields() {
        while (grassList.size() != grassFields) {
            int x = ThreadLocalRandom.current().nextInt(0, (int) (Math.sqrt(grassFields * 10) + 1));
            int y = ThreadLocalRandom.current().nextInt(0, (int) (Math.sqrt(grassFields * 10) + 1));

            placeGrass(new Grass(new Vector2d(x, y)));
        }
    }

    protected Vector2d upperRight() {
        Vector2d result = new Vector2d(0, 0);

        for (Animal animal : animalList) {
            result = result.upperRight(animal.getPosition());
        }

        for (Grass grass : grassList) {
            result = result.upperRight(grass.getPosition());
        }

        return result;
    }

    public void placeGrass(Grass grass) {
        if (!isOccupiedByGrass(grass.getPosition())) {
            grassList.add(grass);
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
        for (Grass grass: grassList) {
            if (grass.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if (super.objectAt(position).equals(Optional.empty())) {
            for (Grass grass: grassList) {
                if (grass.getPosition().equals(position)) {
                    return Optional.of(grass);
                }
            }
            return Optional.empty();
        }
        else return Optional.of(animalList.get(0));
    }
}
