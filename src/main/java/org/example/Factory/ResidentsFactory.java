package org.example.Factory;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Absraction.Config;
import org.example.Configuration.Configurator;
import org.example.Organism.Organism;
import org.reflections.Reflections;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResidentsFactory {
    private final Random random = new Random();
    private Reflections reflections = new Reflections("org.example");

    public Map<Class<? extends Organism>, List<Organism>> createResidents()
    {
        Map<Class<? extends Organism>, List<Organism>> residents = new HashMap<>();
        Set<Organism> prototypes = createPrototypes();


        for (Organism organism : prototypes)
        {
            List<Organism> organismList = new ArrayList<>();
            int randomAmount = random.nextInt(organism.getMaxAmountPerCell());

            for (int i = 0; i < randomAmount; i++)
            {
                organismList.add(organism.reproduce());
            }

            residents.put(organism.getClass(), organismList);
        }

        return residents;
    }


    private Set<Organism> createPrototypes()
    {
        return reflections
                .getSubTypesOf(Organism.class)
                .stream()
                .filter(e -> e.isAnnotationPresent(Config.class))
                .map(Configurator::loadObject)
                .collect(Collectors.toSet());
    }
}
