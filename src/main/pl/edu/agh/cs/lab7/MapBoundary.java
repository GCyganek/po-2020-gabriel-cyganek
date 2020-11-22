package pl.edu.agh.cs.lab7;

import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab5.IMapElement;

import java.util.ArrayList;

public class MapBoundary implements IPositionChangeObserver {
    ArrayList<IMapElement> xAxisMapElements = new ArrayList<>();
    ArrayList<IMapElement> yAxisMapElements = new ArrayList<>();

    public MapBoundary() {}

    public MapBoundary(ArrayList<IMapElement> elements) {
        for (IMapElement element : elements) {
            xAxisMapElements.add(element);
            yAxisMapElements.add(element);
        }

        sortAxes();
    }

    public void addElement(IMapElement element) {
        xAxisMapElements.add(element);
        yAxisMapElements.add(element);

        sortAxes();
    }


    private void sortAxes() {
        xAxisMapElements.sort(IMapElement::compareByXThenByYThenByPriorityDecreasing);
        yAxisMapElements.sort(IMapElement::compareByYThenByXThenByPriorityDecreasing);
    }

    // jesli zwierzak porusza sie nie naruszajac skrajnych indeksow, to nie sortuję
    // jesli zwierzak naruszy skrajny indeks, sortuję
    // jesli ruszam do tylu zwierzakiem, ktory jest najdalej wysunietym na dowolnej z osi, to sortuję
    // jesli ruszam do przodu zwierzakiem, ktory jest najblizej 0 na dowolnej z osi, to sortuje
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {

        // najdalej wysuniete zwierze na osi x cofa || zwierze najblizej 0 na osi x rusza do przodu ||
        // najdalej wysuniete zwierze na osi y cofa || zwierze najblizej 0 na osi y rusza do przodu
        if ((movedElement == xAxisMapElements.get(0) && oldPosition.x > newPosition.x) ||
                (movedElement == xAxisMapElements.get(xAxisMapElements.size() - 1) && oldPosition.x < newPosition.x) ||
                (movedElement == yAxisMapElements.get(0) && oldPosition.y > newPosition.y) ||
                (movedElement == yAxisMapElements.get(yAxisMapElements.size() - 1) && oldPosition.y < newPosition.y)) {
            sortAxes();
        }

        //zwierze, ktore nie bylo na skrajnym indeksie narusza skrajny indeks
        if ((movedElement != xAxisMapElements.get(0) && newPosition.x >= xAxisMapElements.get(0).getPosition().x) ||
                (movedElement != xAxisMapElements.get(xAxisMapElements.size() - 1) && newPosition.x <= xAxisMapElements.get(xAxisMapElements.size() - 1).getPosition().x) ||
                (movedElement != yAxisMapElements.get(0) && newPosition.y >= yAxisMapElements.get(0).getPosition().y) ||
                (movedElement != yAxisMapElements.get(yAxisMapElements.size() - 1) && newPosition.y <= yAxisMapElements.get(yAxisMapElements.size() - 1).getPosition().y)) {
            sortAxes();
        }
    }

    public Vector2d lowerLeft() {
        return new Vector2d(xAxisMapElements.get(xAxisMapElements.size() - 1).getPosition().x,
                yAxisMapElements.get(yAxisMapElements.size() - 1).getPosition().y);
    }

    public Vector2d upperRight() {
        return new Vector2d(xAxisMapElements.get(0).getPosition().x,
                yAxisMapElements.get(0).getPosition().y);
    }
}
