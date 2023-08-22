package org.example.Organism.Animal;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Absraction.Movable;
import org.example.Map.Cell;
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
    private double maxHealth;
    private int speed;
    private double health;

    private Map<String, Integer> targetMatrix = new HashMap<>();
    protected static final Reflections reflections = new Reflections("org.example");

    public abstract void findFood();
    public void tryReproduce() {
        if (thisAnimalSizeList() >= 2) {
            Organism organism = this.reproduce();
            List<Organism> thisOrganismList = this.getCell().getResidents().get(this.getClass());
            if (thisOrganismList != null) {
                thisOrganismList.add(organism);
            }
        }
    }
    public synchronized void move() {
        Cell currentCell = this.getCell();
        List<Cell> neighborCells = new ArrayList<>(currentCell.getNeighboursCells());

        if (!neighborCells.isEmpty()) {
            Cell nextCell = neighborCells.stream().findAny().orElse(null);

            Map<Class<? extends Organism>, List<Organism>> currentCellResidentsCopy = new HashMap<>(currentCell.getResidents());
            Map<Class<? extends Organism>, List<Organism>> nextCellResidentsCopy = new HashMap<>(nextCell.getResidents());

            List<Organism> thisOrganismList = currentCellResidentsCopy.get(this.getClass());

            if (thisOrganismList != null && thisOrganismList.contains(this)) {
                thisOrganismList = new ArrayList<>(thisOrganismList);
                thisOrganismList.remove(this);

                List<Organism> nextCellOrganismList = nextCellResidentsCopy.computeIfAbsent(this.getClass(), k -> new ArrayList<>());
                nextCellOrganismList = new ArrayList<>(nextCellOrganismList);
                nextCellOrganismList.add(this);

                synchronized (currentCell) {
                    currentCellResidentsCopy.put(this.getClass(), thisOrganismList);
                    currentCell.setResidents(currentCellResidentsCopy);
                }

                synchronized (nextCell) {
                    nextCellResidentsCopy.put(this.getClass(), nextCellOrganismList);
                    nextCell.setResidents(nextCellResidentsCopy);
                }

                this.setCell(nextCell);
            }
        }
    }

    public void increaseHunger(int damage) {
        this.setHealth(this.getHealth() - damage);
    }
    public boolean isHealthNull() {
        return this.getHealth() <= 0;
    }
    private int thisAnimalSizeList() { return this.getCell().getResidents().get(this.getClass()).size();}
    protected boolean isHunger() {
        return this.getHealth() <= this.getMaxHealth() - this.getHealth();
    }
    protected Integer getChanceEat(Class<? extends Organism> organism) { return this.getTargetMatrix().get(organism.getSimpleName());}
    protected void restoreHealth(double weight) {
        this.setHealth(this.getHealth() + weight);
    }
}