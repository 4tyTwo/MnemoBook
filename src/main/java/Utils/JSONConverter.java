package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONConverter {
    public static <T> T convert(String path, Class<T> c) {
        try {
            return new ObjectMapper().readValue(new File(path), c);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
