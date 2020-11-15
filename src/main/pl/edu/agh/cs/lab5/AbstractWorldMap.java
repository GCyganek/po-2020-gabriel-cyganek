package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab4.MapVisualiser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap {
    protected static final Vector2d lowerLeft = new Vector2d(0, 0);
    private final MapVisualiser thisMapVisualiser = new MapVisualiser(this);
    protected ArrayList<Animal> animalList = new ArrayList<>();
    protected HashMap<Vector2d, Animal> animalHashMap = new HashMap<>();

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
            animalHashMap.put(animal.getPosition(), animal);
            animalList.add(animal);
            return true;
        }
    }

    @Override
    // nie uzywam values z animalHashMap zamiast animalList, gdyz chce zachowac pierwotna kolejnosc przemieszczania
    // zwierzat, czyli kazde po kolei biorac pod uwage ich czas dodania do mapy. Przy wykorzystaniu animalHashMap
    // ta kolejnosc zostalaby zachwiana podczas usuwania i dodawania zwierzecia do mapy z nowa pozycja po przemieszczeniu
    public void run(List<MoveDirection> directions) {
        int animalIndex = 0;
        for (MoveDirection direction : directions) {
            Animal currentAnimal = animalList.get(animalIndex);
            Vector2d currentPosition = animalHashMap.get(currentAnimal.getPosition()).getPosition();
            currentAnimal.move(direction);
            if (currentAnimal.getPosition() != currentPosition) {
                animalHashMap.remove(currentPosition);
                animalHashMap.put(currentAnimal.getPosition(), currentAnimal);
            }
            animalIndex = (animalIndex + 1) % animalList.size();
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animalHashMap.containsKey(position);
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if (animalHashMap.containsKey(position)) {
            return Optional.of(animalHashMap.get(position));
        }
        return Optional.empty();
    }
}
