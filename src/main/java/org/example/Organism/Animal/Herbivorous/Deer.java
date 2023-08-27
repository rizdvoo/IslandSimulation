package org.example.Organism.Animal.Herbivorous;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Absraction.Config;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Config(filePath = "Animal/Herbivorous/Deer.json")
public class Deer extends Herbivorous {
    @Override
    public Deer reproduce() {
        return Deer.builder()
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
