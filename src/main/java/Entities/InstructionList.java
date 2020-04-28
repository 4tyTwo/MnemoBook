package Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class InstructionList {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public InstructionList(@JsonProperty("instructions") List<Instruction> instructionList) {
        this.instructionList = instructionList;
    }

    @Getter
    private final List<Instruction> instructionList;
}
