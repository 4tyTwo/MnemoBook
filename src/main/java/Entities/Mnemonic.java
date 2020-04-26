package Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Mnemonic {
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("arguments")
    private List<String> arguments;

    public String getName() {
        return name;
    }

    public Mnemonic setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Mnemonic setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public Mnemonic setArguments(List<String> arguments) {
        this.arguments = arguments;
        return this;
    }

    @Override
    public String toString() {
        return "Mnemonic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}
