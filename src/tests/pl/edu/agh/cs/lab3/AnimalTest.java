package pl.edu.agh.cs.lab3;

import org.junit.jupiter.api.Test;
import pl.edu.agh.cs.lab2.MapDirection;
import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    @Test
    public void testAnimalMovesRight() {
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        assertEquals(animal.getOrientation(), MapDirection.EAST);
    }

    @Test
    public void testAnimalMovesLeft() {
        Animal animal = new Animal();
        animal.move(MoveDirection.LEFT);
        assertEquals(animal.getOrientation(), MapDirection.WEST);
    }

    @Test
    public void testAnimalMovesForwardInArea() {
        Animal animal = new Animal();
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.getPosition(), new Vector2d(2, 3));
    }

    @Test
    public void testAnimalMovesBackwardInArea() {
        Animal animal = new Animal();
        animal.move(MoveDirection.BACKWARD);
        assertEquals(animal.getPosition(), new Vector2d(2, 1));
    }

    @Test
    public void testAnimalMovesForwardOutsideArea() {
        Animal animal = new Animal();
        Vector2d newPosition = new Vector2d(2, 4);
        animal.setPosition(newPosition);
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.getPosition(), newPosition);
    }

    @Test
    public void testAnimalMovesBackwardOutsideArea() {
        Animal animal = new Animal();
        Vector2d newPositon = new Vector2d(2, 0);
        animal.setPosition(newPositon);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(animal.getPosition(), newPositon);
    }
}
