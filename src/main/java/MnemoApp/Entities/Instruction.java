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
            @JsonProperty("arguments") List<String> arguments,
            @JsonProperty("full_name") String fullName,
            @JsonProperty("machine_codes") List<String> machineCodes
    ) {
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.operation = operation;
        this.example = example;
        this.arguments = arguments;
        this.machineCodes = machineCodes;
    }

    private final String name;

    private final String fullName;

    public List<String> getMachineCodes() {
        return machineCodes;
    }

    private final List<String> machineCodes;

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

    public List<String> getArguments() {
        return arguments;
    }

    private final String description;

    private final String operation;

    private final String example;

    private final List<String> arguments;

    @Override
    public int compareTo(Instruction other) {
        return this.getName().compareTo(other.getName());
    }
}
