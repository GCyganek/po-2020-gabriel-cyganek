package pl.edu.agh.cs.lab3;

import pl.edu.agh.cs.lab2.MoveDirection;

import java.util.LinkedList;

public class OptionsParser {

    public LinkedList<MoveDirection> parse(String[] args) {
        LinkedList<MoveDirection> directions = new LinkedList<>();
        for (String arg: args) {
            switch (arg) {
                case "f", "forward" -> directions.addLast(MoveDirection.FORWARD);
                case "b", "backward" -> directions.addLast(MoveDirection.BACKWARD);
                case "r", "right" -> directions.addLast(MoveDirection.RIGHT);
                case "l", "left" -> directions.addLast(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }
        return directions;
    }

}
