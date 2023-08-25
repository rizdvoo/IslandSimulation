package org.example.Management;

import org.example.Configuration.Configurator;
import org.example.Factory.FieldFactory;
import org.example.Map.Field;
import org.example.Organism.Animal.Animal;
import org.example.Organism.Animal.Herbivorous.Rabbit;
import org.example.Organism.Animal.Predator.Bear;
import org.example.Organism.Organism;
import org.example.Simulation.*;

import java.util.List;

public class Manager
{
    FieldFactory fieldFactory = new FieldFactory();
    public void start()
    {
        Field field = fieldFactory.create();
        Engine engine = new Engine(field, 10);
        try {
            engine.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
