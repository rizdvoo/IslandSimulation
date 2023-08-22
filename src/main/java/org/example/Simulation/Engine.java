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

public class Engine {
    private final Field field;
    private final int simulationSteps;

    public Engine(Field field, int simulationSteps) {
        this.field = field;
        this.simulationSteps = simulationSteps;
    }
    public void run() {
        for (int step = 0; step < simulationSteps; step++) {
            performPlantActions();
            performAnimalActions();
            updateFieldState();
            printSimulationStatistics(step);
        }
    }
    private void performPlantActions() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        TaskForPlant taskForPlant = new TaskForPlant(field);
        executorService.execute(taskForPlant);
        executorService.close();
    }
    private void performAnimalActions() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        TaskForAnimal taskForAnimal = new TaskForAnimal(field);
        executorService.execute(taskForAnimal);
        executorService.close();
    }
    private void updateFieldState() {
        for (Cell[] cellRow : field.getCells()) {
            for (Cell cell : cellRow) {
                updateCellState(cell);
            }
        }
    }
    private void updateCellState(Cell cell) {
        Map<Class<? extends Organism>, List<Organism>> residents = cell.getResidents();
        for (List<Organism> organisms : residents.values()) {
            organisms.removeIf(Organism::isDead);
            checkHealth(organisms);
        }
    }
    private void checkHealth(List<Organism> organisms) {
        Iterator<Organism> iterator = organisms.iterator();
        while (iterator.hasNext()) {
            Organism organism = iterator.next();
            if (organism instanceof Animal animal) {
                if (animal.isHealthNull()) {
                    iterator.remove();
                }
            }
        }
    }

    private void printSimulationStatistics(int step) {
        System.out.println("Simulation step: " + step);
        int totalOrganisms = 0;
        int totalPlants = 0;
        int totalHerbivores = 0;
        int totalPredators = 0;

        for (Cell[] cellRow : field.getCells()) {
            for (Cell cell : cellRow) {
                for (List<Organism> organisms : cell.getResidents().values()) {
                    for (Organism organism : organisms) {
                        totalOrganisms++;
                        if (organism instanceof Plant) {
                            totalPlants++;
                        } else if (organism instanceof Herbivorous) {
                            totalHerbivores++;
                        } else if (organism instanceof Predator) {
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
