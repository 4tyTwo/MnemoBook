package Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class Instruction implements Comparable<Instruction> {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Instruction(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("operation") String operation,
            @JsonProperty("example")String example,
            @JsonProperty("arguments") List<Argument> arguments,
            @JsonProperty("full_name") String fullName
    ) {
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.operation = operation;
        this.example = example;
        this.arguments = arguments;
    }

    @Getter
    private final String name;

    @Getter
    private final String fullName;

    @Getter
    private final String description;

    @Getter
    private final String operation;

    @Getter
    private final String example;

    @Getter
    private final List<Argument> arguments;

    @Override
    public int compareTo(Instruction other) {
        return this.getName().compareTo(other.getName());
    }
}
