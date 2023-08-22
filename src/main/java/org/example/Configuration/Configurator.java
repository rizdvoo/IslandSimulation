package org.example.Configuration;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Getter;
import org.example.Absraction.Config;
import org.example.Factory.FieldFactory;
import org.example.Map.Field;

import java.io.IOException;
import java.net.URL;

@Getter
public class Configurator
{
    public static URL getFilePath(Class<?> type)
    {
        Config config = type.getAnnotation(Config.class);
        return type.getClassLoader().getResource(config.filePath());
    }

    public static <T> T loadObject(Class<T> type)
    {
        JsonMapper json = new JsonMapper();
        try
        {
            return json.readValue(Configurator.getFilePath(type), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
