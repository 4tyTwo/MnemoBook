package Entities;

import Utils.JSONConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class InstructionList {

    public static final List<Instruction> INSTRUCTION_LIST = JSONConverter.convert(
            "src/main/resources/descriptions/mnemonics.json",
            Instructions.class
    ).instructionList.stream().sorted().collect(Collectors.toList());

    private static class Instructions {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Instructions(@JsonProperty("instructions") List<Instruction> instructionList) {
            this.instructionList = instructionList;
        }

        @Getter
        private final List<Instruction> instructionList;
    }
}
