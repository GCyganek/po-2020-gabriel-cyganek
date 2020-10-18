package pl.edu.agh.po.lab01;

import pl.edu.agh.cs.lab1.Direction;

public class World {
    public static void main(String[] args) {
        System.out.println("start");
        Direction[] directions = change(args);
        run(directions);
        System.out.println("stop");
    }

    public static Direction[] change(String[] args) {
        int i = 0;
        Direction[] directions = new Direction[args.length];
        for (String arg : args) {
            switch (arg) {
                case "f" -> directions[i] = Direction.FORWARD;
                case "b" -> directions[i] = Direction.BACKWARD;
                case "l" -> directions[i] = Direction.LEFT;
                case "r" -> directions[i] = Direction.RIGHT;
                default -> { }
            };
            i += 1;
        }
        return directions;
    }

    public static void run(Direction[] args) {
        for (Direction arg : args) {
            if (arg == null) {
                continue;
            }
            switch (arg) {
                case FORWARD -> System.out.print("Do przodu, ");
                case BACKWARD -> System.out.print("Do tylu, ");
                case LEFT -> System.out.print("W lewo, ");
                case RIGHT -> System.out.print("W prawo, ");
            };
        }
        System.out.println();
    }
}

