package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab3.OptionsParser;
import pl.edu.agh.cs.lab4.IWorldMap;

import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            new Animal(map);
            //new Animal(map);
            new Animal(map, new Vector2d(3,4));
            map.run(directions);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
