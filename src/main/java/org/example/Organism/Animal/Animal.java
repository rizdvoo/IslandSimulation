package org.example.Organism.Animal;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Absraction.Movable;
import org.example.Map.Cell;
import org.example.Organism.Animal.Herbivorous.Herbivorous;
import org.example.Organism.Organism;
import org.reflections.Reflections;

import java.util.*;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class Animal extends Organism implements Movable
{
    private static long amount = 1L;
    @Builder.Default
    private final long serialNumber = amount++;

    private int maxSpeed;
    private int speed;



    private Map<String, Integer> targetMatrix = new HashMap<>();
    protected static final Reflections reflections = new Reflections("org.example");

    @Override
    public void play()
    {
        this.findFood();
        this.tryReproduce();
        this.increaseHunger();
        for (int i = 0; i < this.getSpeed(); i++)
        {
            this.move();
        }
    }

    public abstract void findFood();
    public void tryReproduce()
    {
        if (this.getCell().getSerialNumber() % 10 == 0)
        {
            Organism organism = this.reproduce();
            Cell cell = this.getCell();
            List<Organism> thisOrganismList = cell.getResidents().get(this.getClass());
            thisOrganismList.add(organism);

        }
    }
    public void move()
    {
        Cell currentCell = this.getCell();
        List<Cell> neighborCells = currentCell.getNeighboursCells();

        if (!neighborCells.isEmpty())
        {
            Cell nextCell = neighborCells.stream().findAny().orElse(null);

            Map<Class<? extends Organism>, List<Organism>> currentCellResidents = currentCell.getResidents();
            Map<Class<? extends Organism>, List<Organism>> nextCellResidents = nextCell.getResidents();

            List<Organism> currentOrganismList = currentCellResidents.get(this.getClass());
            List<Organism> nextCellOrganismList = nextCellResidents.get(this.getClass());

            if (currentOrganismList != null && currentOrganismList.contains(this))
            {
                synchronized (currentCell)
                {
                    currentOrganismList.remove(this);
                }

                synchronized (nextCell)
                {
                    nextCellOrganismList.add(this);
                }

                this.setCell(nextCell);
            }
        }
    }

    public void increaseHunger() { this.setHealth(this.getHealth() - randomDamage()); }
    protected int randomDamage()
    {
        Random random = new Random();
        return random.nextInt(10,40);
    }
    protected boolean isHunger() { return this.getHealth() < getMaxHealth(); }
    protected Integer getChanceEat(Class<? extends Organism> organism) { return this.getTargetMatrix().get(organism.getSimpleName());}
    protected void restoreHealth(double weight) { this.setHealth(this.getHealth() + weight);}
}