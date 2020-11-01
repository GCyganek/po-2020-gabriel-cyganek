package pl.edu.agh.cs.lab3;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab4.RectangularMap;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(3,4)));
        map.run(directions);
    }
}
