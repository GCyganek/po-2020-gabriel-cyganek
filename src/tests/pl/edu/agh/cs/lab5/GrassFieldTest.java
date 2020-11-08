package pl.edu.agh.cs.lab5;

import org.junit.jupiter.api.Test;
import pl.edu.agh.cs.lab2.MapDirection;
import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void testCanMoveToUpperBorder() {
        GrassField map = new GrassField(10);

        //before placing animal on upper right corner
        assertTrue(map.canMoveTo(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)));

        map.place(new Animal(map, new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)));

        //after placing animal on upper right corner
        assertFalse(map.canMoveTo(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)));
    }

    @Test
    void testCanMoveToFieldWithGrass() {
        GrassField map = new GrassField(1);

        int max = (int) Math.sqrt(10);

        for (int i = 0; i <= max; i++) {  // na ktoryms z tych pol na pewno jest pole z trawa
            for (int j = 0; j <= max; j++) {
                assertTrue(map.canMoveTo(new Vector2d(i, j)));
            }
        }
    }

    @Test
    void testCanMoveToFieldOutsideTheMap() {
        GrassField map = new GrassField(10);

        assertFalse(map.canMoveTo(new Vector2d(-1, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, -1)));
    }

    @Test
    void testPlaceTwoAnimalsOnTheSameField() {
        GrassField map = new GrassField(10);

        assertTrue(map.place(new Animal(map, new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE))));
        assertFalse(map.place(new Animal(map, new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE))));
    }

    @Test
    void testPlaceOnFieldWithGrass() {
        GrassField map = new GrassField(1);

        int max = (int) Math.sqrt(10);

        for (int i = 0; i <= max; i++) {  // na ktoryms z tych pol na pewno jest pole z trawa
            for (int j = 0; j <= max; j++) {
                assertTrue(map.place(new Animal(map, new Vector2d(i, j))));
            }
        }
    }

    @Test
    void testPlaceOnFieldOutsideOfTheMap() {
        GrassField map = new GrassField(10);

        map.place(new Animal(map, new Vector2d(-1, 0)));
    }

    @Test
    void testRunWithPossibilityToMoveOutsideTheMapAndForTwoAnimalsToCollide() {
        LinkedList<MoveDirection> directions = new LinkedList<>(Arrays.asList(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD));

        GrassField map = new GrassField(10);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map, new Vector2d(3, 4));

        map.place(animal1);
        map.place(animal2);

        map.run(directions);
        assertEquals(animal1.getOrientation(), MapDirection.SOUTH);
        assertEquals(animal1.getPosition(), new Vector2d(2, 0));
        assertEquals(animal2.getOrientation(), MapDirection.NORTH);
        assertEquals(animal2.getPosition(), new Vector2d(3, 7));
    }

    @Test
    void testIsOccupiedByAnimal() {
        GrassField map = new GrassField(10);

        Animal animal1 = new Animal(map, new Vector2d(5, 5));
        map.place(animal1);

        assertTrue(map.isOccupied(animal1.getPosition()));
    }

    @Test
    void testIsOccupiedByGrass() {
        GrassField map = new GrassField(1);

        // wiem na pewno, ze grass pojawi sie w kwadracie o lewym dolnym rogu w (0, 0) i prawym gornym w (3, 3), ale
        // grass jest wstawiane losowo, wiec sprawdzam po prostu, czy ktores z pol w tym kwadracie jest zajete, bo
        // ktores musi byc
        assertTrue(map.isOccupied(new Vector2d(0, 0)) || map.isOccupied(new Vector2d(0, 1)) ||
                map.isOccupied(new Vector2d(0, 2)) || map.isOccupied(new Vector2d(0, 3)) ||
                map.isOccupied(new Vector2d(1, 0)) || map.isOccupied(new Vector2d(1, 1)) ||
                map.isOccupied(new Vector2d(1, 2)) || map.isOccupied(new Vector2d(1, 3)) ||
                map.isOccupied(new Vector2d(2, 0)) || map.isOccupied(new Vector2d(2, 1)) ||
                map.isOccupied(new Vector2d(2, 2)) || map.isOccupied(new Vector2d(2, 3)) ||
                map.isOccupied(new Vector2d(3, 0)) || map.isOccupied(new Vector2d(3, 1)) ||
                map.isOccupied(new Vector2d(3, 2)) || map.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    void testObjectAtOnFieldWithAnimalAndRandomField() {
        GrassField map = new GrassField(10);

        Grass grass = new Grass(new Vector2d(4,4));

        Animal animal = new Animal(map, new Vector2d(5, 5));
        map.place(animal);

        assertEquals(Optional.of(animal), map.objectAt(animal.getPosition()));

        // tutaj sprawdzam, czy na danym polu, gdzie na pewno nie ma animal jest grass lub empty
        assertTrue(map.objectAt(new Vector2d(1, 1)).equals(Optional.of(grass)) ||
                map.objectAt(new Vector2d(1, 1)).equals(Optional.empty()));
    }
}