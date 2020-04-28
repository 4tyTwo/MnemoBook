package Entities;

import Utils.JSONConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class InstructionList {

    public static final List<Instruction> INSTRUCTION_LIST = JSONConverter.convert(
            "src/main/resources/descriptions/mnemonics.json",
            Instructions.class
    ).instructionList;

    private static class Instructions {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Instructions(@JsonProperty("instructions") List<Instruction> instructionList) {
            this.instructionList = instructionList;
        }

        @Getter
        private final List<Instruction> instructionList;
    }
}
