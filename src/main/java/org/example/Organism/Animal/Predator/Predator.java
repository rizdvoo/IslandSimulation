package org.example.Organism.Animal.Predator;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Map.Cell;
import org.example.Organism.Animal.Animal;
import org.example.Organism.Animal.Herbivorous.Herbivorous;
import org.example.Organism.NonOrganism;
import org.example.Organism.Organism;
import org.example.Organism.Plant.Plant;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class Predator extends Animal
{
    private final Random random = new Random();
    private static List<Class<? extends Herbivorous>> victimClasses = new ArrayList<>(reflections.getSubTypesOf(Herbivorous.class));

    @Override
    public void findFood()
    {
        Class<? extends Herbivorous> victimClass = getRandomVictimClass();
        Integer chance = getChanceEat(victimClass);
        int randomNumber = random.nextInt(chance) + 1;

        if (randomNumber <= chance)
        {
            while (isHunger() && eatVictim(victimClass) instanceof Herbivorous victim)
            {
                restoreHealth(victim.getWeight());
            }
        }
    }
    private Organism eatVictim(Class<? extends Herbivorous> victimClass)
    {
        Cell cell = this.getCell();
        if (cell != null) {
            List<Organism> residents = cell.getResidents().get(victimClass);
            if (!residents.isEmpty())
            {
                int randomIndex = random.nextInt(residents.size());
                Organism victim = residents.get(randomIndex);
                victim.setDead(true);
                return victim;
            }
        }
        return new NonOrganism();
    }
    private Class<? extends Herbivorous> getRandomVictimClass()
    {
        return victimClasses.get(random.nextInt(victimClasses.size()));
    }
}
