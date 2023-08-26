package org.example.Organism.Plant;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Map.Cell;
import org.example.Organism.Organism;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Plant extends Organism
{
    private static long amount = 1L;
    @Builder.Default
    private final long serialNumber = amount++;

    @Override
    public void play()
    {
        increaseHunger();
        this.tryReproduce();
    }

    public void tryReproduce()
    {
        if (this.getCell().getSerialNumber() % 2 == 0) {
            Organism organism = this.reproduce();
            Cell cell = this.getCell();
            List<Organism> thisOrganismList = cell.getResidents().get(this.getClass());
            thisOrganismList.add(organism);
        }
    }
    public void increaseHunger() { this.setHealth(this.getHealth() - randomDamage()); }

    protected int randomDamage()
    {
        Random random = new Random();
        return random.nextInt(2,5);
    }
}
