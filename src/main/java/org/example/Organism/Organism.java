package org.example.Organism;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.Absraction.Reproducible;
import org.example.Map.Cell;

import java.util.function.Supplier;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class Organism implements Reproducible
{
    @JsonIgnore
    private Cell cell;
    private double weight;
    private double maxWeight;
    private int maxAmountPerCell;
    private boolean isDead = false;

    public abstract void play();
}
