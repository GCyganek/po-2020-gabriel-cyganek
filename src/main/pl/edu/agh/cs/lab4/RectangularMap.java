package pl.edu.agh.cs.lab4;

import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab5.AbstractWorldMap;


public class RectangularMap extends AbstractWorldMap {
    private final Vector2d upperRight;
    private final Vector2d lowerLeft = new Vector2d(0, 0);

    public RectangularMap(int width, int height) {
        this.upperRight = new Vector2d(width, height);
    }

    @Override
    protected Vector2d upperRight() {
        return upperRight;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(lowerLeft) && position.precedes(upperRight)) {
            return !isOccupied(position);
        }
        return false;
    }
}
