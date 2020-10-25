package pl.edu.agh.cs.lab3;

import org.junit.jupiter.api.Test;
import pl.edu.agh.cs.lab2.MapDirection;
import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    public void testWholeProgramInAreaWithGoodArguments() {
        String[] args = {"f", "forward", "b", "backward", "r", "right", "l", "left"};
        OptionsParser optionsParser = new OptionsParser();
        LinkedList<MoveDirection> directions = optionsParser.parse(args);
        assertEquals(optionsParser.parse(args),
                new LinkedList<>(Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT,
                        MoveDirection.LEFT, MoveDirection.LEFT)));

        Animal animal = new Animal();
        Vector2d[] allPositions = {new Vector2d(2, 3), new Vector2d(2, 4), new Vector2d(2, 3), new Vector2d(2, 2)};
        MapDirection[] allDirections = {MapDirection.EAST, MapDirection.SOUTH, MapDirection.EAST, MapDirection.NORTH};
        int positionIndex = 0;
        int directionIndex = 0;

        for (MoveDirection direction : directions) {
            animal.move(direction);
            if (direction == MoveDirection.RIGHT || direction == MoveDirection.LEFT) {
                assertEquals(animal.getOrientation(), allDirections[directionIndex]);
                directionIndex += 1;
            } else {
                assertEquals(animal.getPosition(), allPositions[positionIndex]);
                positionIndex += 1;
            }
        }
    }

    @Test
    public void testWholeProgramInAreaWithBadArguments() {
        String[] args = {"wrongString1", "wrongString2","f", "forward", "b", "backward", "r", "right", "l", "left"};
        OptionsParser optionsParser = new OptionsParser();
        LinkedList<MoveDirection> directions = optionsParser.parse(args);
        assertEquals(optionsParser.parse(args),
                new LinkedList<>(Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT,
                        MoveDirection.LEFT, MoveDirection.LEFT)));

        Animal animal = new Animal();
        Vector2d[] allPositions = {new Vector2d(2, 3), new Vector2d(2, 4), new Vector2d(2, 3), new Vector2d(2, 2)};
        MapDirection[] allDirections = {MapDirection.EAST, MapDirection.SOUTH, MapDirection.EAST, MapDirection.NORTH};
        int positionIndex = 0;
        int directionIndex = 0;

        for (MoveDirection direction: directions) {
            animal.move(direction);
            if (direction == MoveDirection.RIGHT || direction == MoveDirection.LEFT) {
                assertEquals(animal.getOrientation(), allDirections[directionIndex]);
                directionIndex += 1;
            } else {
                assertEquals(animal.getPosition(), allPositions[positionIndex]);
                positionIndex += 1;
            }
        }
    }

    @Test
    public void testWholeProgramOutsideAreaWithBadArguments() {
        String[] args = {"wrongString1", "wrongString2","f", "forward", "f", "r", "f", "f", "f", "f"};
        OptionsParser optionsParser = new OptionsParser();
        LinkedList<MoveDirection> directions = optionsParser.parse(args);
        assertEquals(optionsParser.parse(args),
                new LinkedList<>(Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD,
                    MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.FORWARD, MoveDirection.FORWARD)));

        Animal animal = new Animal();
        Vector2d[] allPositions = {new Vector2d(2, 3), new Vector2d(2, 4), new Vector2d(2, 4),
                new Vector2d(3, 4), new Vector2d(4, 4), new Vector2d(4, 4), new Vector2d(4, 4)};
        MapDirection[] allDirections = {MapDirection.EAST};
        int positionIndex = 0;
        int directionIndex = 0;

        for (MoveDirection direction: directions) {
            animal.move(direction);
            if (direction == MoveDirection.RIGHT || direction == MoveDirection.LEFT) {
                assertEquals(animal.getOrientation(), allDirections[directionIndex]);
                directionIndex += 1;
            } else {
                assertEquals(animal.getPosition(), allPositions[positionIndex]);
                positionIndex += 1;
            }
        }
    }

}