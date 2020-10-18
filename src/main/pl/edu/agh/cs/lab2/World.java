package pl.edu.agh.cs.lab2;

public class World {

    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection direction1 = MapDirection.EAST;
        MapDirection prevDirection = direction1.previous();
        MapDirection nextDirection = direction1.next();
        Vector2d positionVector = direction1.toUnitVector();
        System.out.println(prevDirection + " " + nextDirection + " " + positionVector);
    }
}
