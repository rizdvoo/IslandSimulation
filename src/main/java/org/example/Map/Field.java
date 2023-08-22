package org.example.Map;
import lombok.*;
import org.example.Absraction.Config;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Config(filePath = "Map/Field.json")
public class Field
{
    private int width;
    private int height;
    private Cell[][] cells = new Cell[width][height];
}
