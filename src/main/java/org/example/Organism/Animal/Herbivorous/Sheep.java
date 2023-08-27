package org.example.Organism.Animal.Herbivorous;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Absraction.Config;
import org.example.Organism.Organism;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Config(filePath = "Animal/Herbivorous/Sheep.json")
public class Sheep extends Herbivorous{
    @Override
    public Sheep reproduce() {
        return Sheep.builder()
                .maxWeight(this.getMaxWeight())
                .maxHealth(this.getMaxHealth())
                .maxSpeed(this.getMaxSpeed())
                .weight(this.getWeight())
                .health(this.getHealth())
                .speed(this.getSpeed())
                .cell(this.getCell())
                .maxAmountPerCell(this.getMaxAmountPerCell())
                .targetMatrix(this.getTargetMatrix())
                .build();
    }
}
