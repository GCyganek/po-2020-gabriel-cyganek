package pl.edu.agh.cs.lab4;

import org.junit.jupiter.api.Test;
import pl.edu.agh.cs.lab2.MapDirection;
import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab3.OptionsParser;
import pl.edu.agh.cs.lab4.RectangularMap;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    public void testWholeProgramWithGoodArgumentsWithAnimalsPlacedCorrectlyWantingToWalkIntoEachOtherAndOustide() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        OptionsParser optionsParser = new OptionsParser();
        LinkedList<MoveDirection> directions = optionsParser.parse(args);
        assertEquals(optionsParser.parse(args),
                new LinkedList<>(Arrays.asList(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                        MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.FORWARD, MoveDirection.FORWARD)));

        IWorldMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map, new Vector2d(3, 4));

        map.run(directions);
        assertEquals(animal1.getOrientation(), MapDirection.SOUTH);
        assertEquals(animal1.getPosition(), new Vector2d(2, 0));
        assertEquals(animal2.getOrientation(), MapDirection.NORTH);
        assertEquals(animal2.getPosition(), new Vector2d(3, 5));
    }

    @Test
    public void testWholeProgramWithGoodArgumentsWithAnimalsPlacedWronglyWantingToWalkIntoEachOtherAndOustide() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        OptionsParser optionsParser = new OptionsParser();
        LinkedList<MoveDirection> directions = optionsParser.parse(args);
        assertEquals(optionsParser.parse(args),
                new LinkedList<>(Arrays.asList(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                        MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.FORWARD, MoveDirection.FORWARD)));

        IWorldMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map, new Vector2d(3, 4));
        assertThrows(IllegalArgumentException.class, () -> {
            Animal animal3 = new Animal(map);
        });
    }

    @Test
    public void testWholeProgramWithBadArguments() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f", "gfdgdfg"};
        OptionsParser optionsParser = new OptionsParser();
        assertThrows(IllegalArgumentException.class, () -> {
            LinkedList<MoveDirection> directions = optionsParser.parse(args);
        });
    }
}