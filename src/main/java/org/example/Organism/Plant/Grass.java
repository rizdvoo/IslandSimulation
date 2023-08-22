package org.example.Organism.Plant;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.Absraction.Config;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Config(filePath = "Plant/Grass.json")
public class Grass extends Plant
{
    @Override
    public Grass reproduce()
    {
        return Grass.builder()
                .weight(this.getWeight())
                .maxAmountPerCell(this.getMaxAmountPerCell())
                .maxWeight(this.getMaxWeight())
                .build();
    }
}
