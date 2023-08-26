package org.example.Organism.Animal.Predator;

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
@Config(filePath = "Animal/Predator/Fox.json")
public class Fox extends Predator{
    @Override
    public Organism reproduce() {
        return Fox.builder()
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
