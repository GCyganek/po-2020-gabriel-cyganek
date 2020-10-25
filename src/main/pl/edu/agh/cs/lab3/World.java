package pl.edu.agh.cs.lab3;

import pl.edu.agh.cs.lab2.MoveDirection;

import java.util.LinkedList;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal);

        OptionsParser optionsParser = new OptionsParser();
        LinkedList<MoveDirection> directions = optionsParser.parse(args);

        for (MoveDirection direction: directions) {
            //System.out.println(direction);
            animal.move(direction);
            //System.out.println(animal);
        }
    }
}
