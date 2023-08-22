package org.example.Tasks;

import org.example.Map.Cell;
import org.example.Map.Field;
import org.example.Organism.Animal.Animal;
import org.example.Organism.Organism;
import org.example.Organism.Plant.Plant;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TaskForPlant implements Runnable {
    private final Field field;
    private final Random random = new Random();

    public TaskForPlant(Field field) {
        this.field = field;
    }

    @Override
    public void run() {
        for (Cell[] cellRow : field.getCells()) {
            for (Cell cell : cellRow) {
                synchronized (cell) {
                    for (List<Organism> organisms : cell.getResidents().values()) {
                        for (Organism organism : organisms) {
                            if (organism instanceof Plant plant) {
                               plant.tryReproduce();
                            }
                        }
                    }
                }
            }
        }
    }
}
