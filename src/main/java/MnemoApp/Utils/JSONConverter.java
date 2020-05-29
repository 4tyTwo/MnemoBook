package MnemoApp.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class JSONConverter {
    public static <T> T convert(InputStream stream, Class<T> c) {
        try {
            return new ObjectMapper().readValue(stream, c);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
