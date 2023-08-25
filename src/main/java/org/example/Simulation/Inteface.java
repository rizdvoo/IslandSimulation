package org.example.Simulation;

import org.example.Map.Cell;
import org.example.Map.Field;
import org.example.Organism.Animal.Herbivorous.Herbivorous;
import org.example.Organism.Animal.Predator.Predator;
import org.example.Organism.Organism;
import org.example.Organism.Plant.Plant;

import java.util.List;

public class Inteface
{
    private static int totalOrganisms = 0;
    private static int totalPlants = 0;
    private static int totalHerbivores = 0;
    private static int totalPredators = 0;
    public static void printSimulationStatistics(int step, Field field)
    {
        System.out.println("Simulation step: " + step);

        for (Cell[] cellRow : field.getCells())
        {
            for (Cell cell : cellRow)
            {
                for (List<Organism> organisms : cell.getResidents().values())
                {
                    for (Organism organism : organisms)
                    {
                        totalOrganisms++;
                        if (organism instanceof Plant)
                        {
                            totalPlants++;
                        }
                        else if (organism instanceof Herbivorous)
                        {
                            totalHerbivores++;
                        }
                        else if (organism instanceof Predator)
                        {
                            totalPredators++;
                        }
                    }
                }
            }
        }

        print();
    }

    private static void print()
    {
        System.out.println("Total organisms: " + totalOrganisms);
        System.out.println("Total plants: " + totalPlants);
        System.out.println("Total herbivores: " + totalHerbivores);
        System.out.println("Total predators: " + totalPredators);
        System.out.println("----------------------------------------------------------------");
    }
}
