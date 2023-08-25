package org.example.Organism.Plant;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Map.Cell;
import org.example.Organism.Organism;

import java.util.List;
import java.util.Optional;

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
        this.tryReproduce();
    }

    public void tryReproduce()
    {
        Organism organism = this.reproduce();
        Cell cell = this.getCell();
        if (cell != null) {
            List<Organism> thisOrganismList = cell.getResidents().get(this.getClass());
            thisOrganismList.add(organism);
        }

    }
}
