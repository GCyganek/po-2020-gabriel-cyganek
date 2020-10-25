package pl.edu.agh.cs.lab3;

import pl.edu.agh.cs.lab2.MapDirection;
import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    private static final Vector2d upperBorder = new Vector2d(4, 4);  // zmienne pomagające przy warunku niewychodzenia animal za mapę
    private static final Vector2d lowerBorder = new Vector2d(0, 0);

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "orientation=" + orientation +
                ", position=" + position +
                '}';
    }

    public void move(MoveDirection direction) {
        switch(direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (lowerBorder.precedes(newPosition) && upperBorder.follows(newPosition)) {
                    position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.substract(orientation.toUnitVector());
                if (lowerBorder.precedes(newPosition) && upperBorder.follows(newPosition)) {
                    position = newPosition;
                }
/*              if (lowerBorder.precedes(position.substract(orientation.toUnitVector())) && upperBorder.follows(position.substract(orientation.toUnitVector()))) {
                    position = position.substract(orientation.toUnitVector());
                }* jeszcze tak ewentualnie mozna, w tym wariancie wyzej zajmuje wiecej pamieci, ale wykonuje mniej obliczen i nie powtarzam ich
                a w tym zakomentowanym nie tworze nowej zmiennej, ale obliczam to samo 3 razy/
 */
            }
        }
    }
}
