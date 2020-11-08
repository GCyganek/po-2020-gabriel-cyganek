/*

10.

11. Na tę chwilę Grass i Animal to jedyne klasy, które dziedziczyłyby po AbstractWorldElement i wydaje się, że
stworzenie takiej klasy abstrakcyjnej, po którą mogłyby rozszerzać jest bezcelowe, gdyż nie zawierają one podobnych
metod, pól, za bardzo się różnią

 */


package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab4.MapVisualiser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap {
    protected final Vector2d lowerLeft = new Vector2d(0, 0);
    private final MapVisualiser thisMapVisualiser = new MapVisualiser(this);
    private int animalCount = 0;
    protected ArrayList<Animal> animalList = new ArrayList<>();

    @Override
    public String toString() {
        return thisMapVisualiser.draw(lowerLeft, upperRight());
    }

    protected abstract Vector2d upperRight();

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public boolean place(Animal animal){
        if (!canMoveTo(animal.getPosition())) {
            return false;
        } else {
            animalList.add(animal);
            animalCount += 1;
            return true;
        }
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
