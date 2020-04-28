package Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class Argument {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Argument(@JsonProperty("name") String name, @JsonProperty("type") String type) {
        this.name = name;
        this.type = type;
    }

    @Getter
    private final String name;

    @Getter
    private final String type;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", type='" + type + '\'';
    }
}
