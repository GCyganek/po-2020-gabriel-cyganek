package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.Vector2d;

public interface IMapElement {
    Vector2d getPosition();
    int getPriorityOnTheMap();

    static int compareByXThenByYThenByPriorityDecreasing(IMapElement element1, IMapElement element2) {
        if (element1.getPosition().x == element2.getPosition().x) {
            if (element1.getPosition().y == element2.getPosition().y) {
                return element2.getPriorityOnTheMap() - element1.getPriorityOnTheMap();
            } else {
                return element2.getPosition().y - element1.getPosition().y;
            }
        }
        return element2.getPosition().x - element1.getPosition().x;
    }

    static int compareByYThenByXThenByPriorityDecreasing(IMapElement element1, IMapElement element2) {
        if (element1.getPosition().y == element2.getPosition().y) {
            if (element1.getPosition().x == element2.getPosition().x) {
                return element2.getPriorityOnTheMap() - element1.getPriorityOnTheMap();
            } else {
                return element2.getPosition().x - element1.getPosition().x;
            }
        }
        return element2.getPosition().y - element1.getPosition().y;
    }

    String toString();
}
