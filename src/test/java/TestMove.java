
import org.example.Absraction.*;
import org.example.Configuration.*;
import org.example.Factory.*;
import org.example.Management.*;
import org.example.Map.*;
import org.example.Organism.*;
import org.example.Organism.Animal.Predator.Bear;
import org.example.Simulation.*;
import org.example.Tasks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMove {
    public static void main(String[] args) {
        Bear bear = Configurator.loadObject(Bear.class);
        Map<Class<? extends Organism>, List<Organism>> listMap1 = new HashMap<>();
        List<Organism> bears1 = new ArrayList<>();
        bears1.add(bear);
        listMap1.put(Bear.class, bears1);

        Cell cell1 = new Cell(listMap1);
        bear.setCell(cell1);


        Map<Class<? extends Organism>, List<Organism>> listMap2 = new HashMap<>();
        List<Organism> bears2 = new ArrayList<>();
        listMap2.put(Bear.class, bears2);

        Cell cell2 = new Cell(listMap2);

        cell1.getNeighboursCells().add(cell2);
        cell2.getNeighboursCells().add(cell1);

        System.out.println("Cell 1: " + cell1.getResidents().values());
        System.out.println("------------------------");
        System.out.println("Cell 2: " + cell2.getResidents().values());

        bear.move();
        System.out.println("+++++++++++++++++++++++++++");

        System.out.println("Cell 1: " + cell1.getResidents().values());
        System.out.println("------------------------");
        System.out.println("Cell 2: " + cell2.getResidents().values());
    }
}
