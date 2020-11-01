package pl.edu.agh.cs.lab4;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RectangularMap implements IWorldMap {
    private final Vector2d upperBorder;
    private final Vector2d lowerBorder = new Vector2d(0, 0);

    public ArrayList<Animal> animalList = new ArrayList<>();
    private int animalCount = 0;
    private final int maxAnimalCount;
    private final MapVisualiser thisMapVisualiser = new MapVisualiser(this);

    public RectangularMap(int width, int height) {
        this.maxAnimalCount = width * height;
        this.upperBorder = new Vector2d(width, height);
    }

    @Override
    public String toString() {
        return thisMapVisualiser.draw(lowerBorder, upperBorder);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(lowerBorder) && position.precedes(upperBorder)) {
            return !isOccupied(position);
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (animalCount < maxAnimalCount) {
            if (!canMoveTo(animal.getPosition())) {
                return false;
            } else {
                animalList.add(animal);
                animalCount += 1;
                return true;
            }
        }
        return false;
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int animalIndex = 0;
        for (MoveDirection direction : directions) {
            animalList.get(animalIndex).move(direction);
            animalIndex = (animalIndex + 1) % animalCount;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animalList) {
            if (animal.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        for (Animal animal : animalList) {
            if (animal.getPosition().equals(position)) {
                return Optional.of(animal);
            }
        }
        return Optional.empty();
    }
}
