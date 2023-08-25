package org.example.Tasks;

import org.example.Map.Cell;
import org.example.Map.Field;
import org.example.Organism.Animal.Animal;
import org.example.Organism.Organism;

import java.util.List;
import java.util.Random;

public class TaskForAnimal
{
    private final Field field;
    private final Random random = new Random();

    public TaskForAnimal(Field field)
    {
        this.field = field;
    }

    public void run() {
        for (Cell[] cellRow : field.getCells())
        {
            for (Cell cell : cellRow)
            {
                synchronized (cell)
                {
                    for (List<Organism> organisms : cell.getResidents().values())
                    {
                        for (Organism organism : organisms)
                        {
                            if (organism instanceof Animal animal)
                            {
                                animal.play();
                            }
                        }
                    }
                }
            }
        }
    }
}
