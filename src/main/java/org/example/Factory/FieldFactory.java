package org.example.Factory;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Configuration.Configurator;
import org.example.Map.Cell;
import org.example.Map.Field;
import org.example.Organism.Organism;

import java.util.*;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FieldFactory {

    private final ResidentsFactory residentsFactory = new ResidentsFactory();

    public Field create() {
        Field field = Configurator.loadObject(Field.class);
        Cell[][] cells = new Cell[field.getWidth()][field.getHeight()];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
        return fill(field, cells);
    }

    private Field fill(Field field, Cell[][] cells) {
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                Map<Class<? extends Organism>, List<Organism>> residents = residentsFactory.createResidents();
                Cell cell = cells[i][j];
                assignOrganismsToCell(residents, cell);
                setCellResidents(cell, residents);
            }
        }
        fillNeighboursCells(cells);
        field.setCells(cells);
        return field;
    }

    private void assignOrganismsToCell(Map<Class<? extends Organism>, List<Organism>> residents, Cell cell) {
        residents.values().stream()
                .flatMap(List::stream)
                .forEach(organism -> organism.setCell(cell));
    }

    private void setCellResidents(Cell cell, Map<Class<? extends Organism>, List<Organism>> residents) {
        cell.setResidents(residents);
    }
    private void fillNeighboursCells(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                List<Cell> neighbors = new ArrayList<>();
                Cell currentCell = cells[i][j];

                addNeighborIfValid(neighbors, i, j + 1, cells); // Right neighbor
                addNeighborIfValid(neighbors, i + 1, j, cells); // Bottom neighbor

                neighbors.removeIf(Objects::isNull);

                for (Cell neighbor : neighbors) {
                    currentCell.addNeighbours(neighbor);
                    neighbor.addNeighbours(currentCell);
                }
            }
        }

    }
    private void addNeighborIfValid(List<Cell> neighbors, int i, int j, Cell[][] cells) {
        if (i >= 0 && i < cells.length && j >= 0 && j < cells[i].length) {
            neighbors.add(cells[i][j]);
        }
    }
}


