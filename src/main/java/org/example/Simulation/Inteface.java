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

    public static void printSimulationStatistics(int step, Field field)
    {
        int totalOrganisms = 0;
        int totalPlants = 0;
        int totalHerbivores = 0;
        int totalPredators = 0;

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

        System.out.println("Total organisms: " + totalOrganisms);
        System.out.println("Total plants: " + totalPlants);
        System.out.println("Total herbivores: " + totalHerbivores);
        System.out.println("Total predators: " + totalPredators);
        System.out.println("----------------------------------------------------------------");
    }
}
