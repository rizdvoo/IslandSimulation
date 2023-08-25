package org.example.Simulation;

import org.example.Map.Cell;
import org.example.Map.Field;
import org.example.Organism.Animal.Animal;
import org.example.Organism.Animal.Herbivorous.Herbivorous;
import org.example.Organism.Animal.Predator.Predator;
import org.example.Organism.Organism;
import org.example.Organism.Plant.Plant;
import org.example.Tasks.TaskForAnimal;
import org.example.Tasks.TaskForPlant;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Engine
{
    private final Field field;
    private final int simulationSteps;

    public Engine(Field field, int simulationSteps)
    {
        this.field = field;
        this.simulationSteps = simulationSteps;
    }
    public void run() throws InterruptedException {
        for (int step = 0; step < simulationSteps; step++) {
            Thread thread = performPlantActions();
            performAnimalActions();
            thread.join();
            updateFieldState();
            Inteface.printSimulationStatistics(step, field);
        }
    }


    private Thread performPlantActions()
    {
        TaskForPlant taskForPlant = new TaskForPlant(field);
        Thread thread = new Thread(taskForPlant);
        thread.start();
        return thread;
    }
    private void performAnimalActions()
    {
        TaskForAnimal taskForAnimal = new TaskForAnimal(field);
        taskForAnimal.run();
    }
    private void updateFieldState()
    {
        for (Cell[] cellRow : field.getCells())
        {
            for (Cell cell : cellRow)
            {
                updateCellState(cell);
            }
        }
    }
    private void updateCellState(Cell cell)
    {
        Map<Class<? extends Organism>, List<Organism>> residents = cell.getResidents();
        for (List<Organism> organisms : residents.values())
        {
            checkIsDead(organisms);
            checkHealth(organisms);
        }
    }
    private void checkHealth(List<Organism> organisms)
    {
        Iterator<Organism> iterator = organisms.iterator();
        while (iterator.hasNext())
        {
            Organism organism = iterator.next();
            if (organism instanceof Animal animal)
            {
                if (animal.isHealthNull())
                {
                    iterator.remove();
                }
            }
        }
    }
    private void checkIsDead(List<Organism> organisms)
    {
        Iterator<Organism> iterator = organisms.iterator();
        while (iterator.hasNext())
        {
            Organism organism = iterator.next();
            if (organism instanceof Animal animal)
            {
                if (animal.isDead())
                {
                    iterator.remove();
                }
            }
        }
    }
}
