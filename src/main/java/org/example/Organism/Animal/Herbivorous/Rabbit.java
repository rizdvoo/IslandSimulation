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
@Config(filePath = "Animal/Herbivorous/Rabbit.json")
public class Rabbit extends Herbivorous
{
    @Override
    public Rabbit reproduce()
    {
        return Rabbit.builder()
                .maxWeight(this.getMaxWeight())
                .maxHealth(this.getMaxHealth())
                .maxSpeed(this.getMaxSpeed())
                .weight(this.getWeight())
                .health(this.getHealth())
                .speed(this.getSpeed())
                .maxAmountPerCell(this.getMaxAmountPerCell())
                .targetMatrix(this.getTargetMatrix())
                .build();
    }
}
