package org.example.Organism.Plant;
import lombok.*;
import lombok.experimental.SuperBuilder;
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

    public synchronized void tryReproduce() {
            Organism organism = this.reproduce();
            List<Organism> thisOrganismList = this.getCell().getResidents().get(this.getClass());
            thisOrganismList.add(organism);
    }
}
