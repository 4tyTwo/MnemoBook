package MnemoApp.Entities;

import MnemoApp.Utils.JSONConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class InstructionList {

    public static final List<Instruction> INSTRUCTION_LIST = JSONConverter.convert(
            InstructionList.class.getResourceAsStream("/mnemonics.json"),
            Instructions.class
    ).instructionList.stream().sorted().collect(Collectors.toList());

    private static class Instructions {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Instructions(@JsonProperty("instructions") List<Instruction> instructionList) {
            this.instructionList = instructionList;
        }

        private final List<Instruction> instructionList;
    }
}
