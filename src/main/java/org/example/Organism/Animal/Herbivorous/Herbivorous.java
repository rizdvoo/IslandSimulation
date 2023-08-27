package org.example.Organism.Animal.Herbivorous;

import lombok.experimental.SuperBuilder;
import org.example.Map.Cell;
import org.example.Organism.Animal.Animal;
import org.example.Organism.NonOrganism;
import org.example.Organism.Organism;
import org.example.Organism.Plant.Grass;
import org.example.Organism.Plant.Plant;

import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.reflections.Reflections;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class Herbivorous extends Animal
{

    private final Random random = new Random();
    private static List<Class<? extends Plant>> plantsClasses = new ArrayList<>(reflections.getSubTypesOf(Plant.class));

    @Override
    public void findFood()
    {
        Class<? extends Plant> plantClass = getRandomPlantClass();
        Integer chance = getChanceEat(plantClass);
        int randomNumber = random.nextInt(chance) + 1;

        if (randomNumber <= chance)
        {
            Organism organism = eatPlant(plantClass);
            while (isHunger() && organism instanceof Plant plant)
            {
                if (this.getHealth() < getMaxHealth()) {
                    restoreHealth(plant.getWeight());
                }
            }
        }
    }
    private Organism eatPlant(Class<? extends Plant> plantClass)
    {
        List<Organism> residents = this.getCell().getResidents().get(plantClass);
        if (!residents.isEmpty())
        {
            int randomIndex = random.nextInt(residents.size());
            Organism plant = residents.get(randomIndex);
            residents.remove(randomIndex);
            plant.setDead(true);
            return plant;
        }
        return new NonOrganism();
    }
    private Class<? extends Plant> getRandomPlantClass()
    {
        return plantsClasses.get(random.nextInt(plantsClasses.size()));
    }
}

