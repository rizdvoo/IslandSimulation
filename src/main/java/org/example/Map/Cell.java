package org.example.Map;
import lombok.*;
import org.example.Organism.Organism;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Cell
{
    private static long amount = 1L;
    private final long serialNumber = amount++;
    private List<Cell> neighboursCells = new ArrayList<>();

    private Map<Class<? extends Organism>, List<Organism>> residents;
    public Cell(Map<Class<? extends Organism>, List<Organism>> residents)
    {
        this.residents = residents;
    }

    public void addNeighbours(Cell cell) {
        neighboursCells.add(cell);
    }

}
