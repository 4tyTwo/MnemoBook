package MnemoApp.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    private final String name;

    private final String fullName;

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getOperation() {
        return operation;
    }

    public String getExample() {
        return example;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    private final String description;

    private final String operation;

    private final String example;

    private final List<Argument> arguments;

    @Override
    public int compareTo(Instruction other) {
        return this.getName().compareTo(other.getName());
    }
}
